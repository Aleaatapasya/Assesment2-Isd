import java.util.*;

class kotaNegara {
    String nama;

    public kotaNegara(String name) {
        this.nama = name;
    }

    @Override
    public String toString() {
        return nama;
    }
}

class Graph {
    private Map<kotaNegara, List<kotaNegara>> adjCities = new HashMap<>();

    public void addCity(kotaNegara kota) {
        adjCities.putIfAbsent(kota, new ArrayList<>());
    }

    public void addEdge(kotaNegara kota1, kotaNegara kota2) {
        adjCities.get(kota1).add(kota2);
        adjCities.get(kota2).add(kota1);
    }

    public List<kotaNegara> getAdjCities(kotaNegara kota) {
        return adjCities.get(kota);
    }

    public Set<kotaNegara> getCities() {
        return adjCities.keySet();
    }
}

public class tipeSoalB {

    private static void PenelusuranKotaTerdekat(Graph graph, kotaNegara start) {
        Set<kotaNegara> visited = new HashSet<>();
        List<kotaNegara> tour = new ArrayList<>();
        kotaNegara current = start;
        visited.add(current);
        tour.add(current);

        while (visited.size() < graph.getCities().size()) {
            kotaNegara nextKota = null;
            for (kotaNegara neighbor : graph.getAdjCities(current)) {
                if (!visited.contains(neighbor)) {
                    nextKota = neighbor;
                    break;
                }
            }
            if (nextKota != null) {
                visited.add(nextKota);
                tour.add(nextKota);
                current = nextKota;
            } else {
                break;
            }
        }

        System.out.println("Penelusuran berdasarkan kota terdekatnya:");
        for (kotaNegara kota : tour) {
            System.out.println(kota.nama);
        }
    }

    private static void PenelusuranKotaTerjauh(List<kotaNegara> cities, kotaNegara start) {
        cities.sort(Comparator.comparing(kota -> kota.nama));

        System.out.println("\nPenelusuran berdasarkan kota terjauhnya:");
        for (kotaNegara kota : cities) {
            System.out.println(kota.nama);
        }
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        // untuk input jumlah kota yang ada
        System.out.println("Ada berapa kota?:");
        int input = userInput.nextInt();
        userInput.nextLine();
        System.out.println("-------------------------------------------------------");
        List<kotaNegara> cities = new ArrayList<>();
        Graph graph = new Graph();

        // untuk input nama nama kota nya
        for (int i = 0; i < input; i++) {
            System.out.println("Masukkan nama kota nya:");
            String nama = userInput.nextLine();
            kotaNegara kota = new kotaNegara(nama);
            cities.add(kota);
            graph.addCity(kota);
        }

        // untuk input jumlah edge nya
        System.out.println("-------------------------------------------------------");
        System.out.println("Ada berapa edge? :");
        int e = userInput.nextInt();
        System.out.println("-------------------------------------------------------");
        userInput.nextLine();

        // untuk input kota kota yang berhubungan
        for (int i = 0; i < e; i++) {
            System.out.println("Tuliskan 2 kota yang berhubungan:");
            String namaKota1 = userInput.nextLine();
            String namaKota2 = userInput.nextLine();
            kotaNegara kota1 = cities.stream().filter(c -> c.nama.equals(namaKota1)).findFirst().orElse(null);
            kotaNegara kota2 = cities.stream().filter(c -> c.nama.equals(namaKota2)).findFirst().orElse(null);
            if (kota1 != null && kota2 != null) {
                graph.addEdge(kota1, kota2);
            } else {
                System.out.println("Mohon maaf nama kota salah, tolong input kembali!!!.");
                i--;
            }
            System.out.println("-------------------------------------------------------");
        }

        // untuk input kota yang awal
        System.out.println("Masukkan nama kota awal:");
        String namaKotaAwal = userInput.nextLine();
        System.out.println("-------------------------------------------------------");
        kotaNegara kotaAwal = cities.stream().filter(c -> c.nama.equals(namaKotaAwal)).findFirst().orElse(null);
        if (kotaAwal == null) {
            System.out.println(namaKotaAwal + " tidak termasuk ke dalam list.");
            return;
        }

        // untuk menampilkan hasil berdasarkan penelusuran kota yang terdekat
        System.out.println(" ");
        PenelusuranKotaTerdekat(graph, kotaAwal);

        // untuk menampilkan hasil berdasarkan penelusuran kota yang terjauh
        System.out.println(" ");
        PenelusuranKotaTerjauh(cities, kotaAwal);
    }
}
