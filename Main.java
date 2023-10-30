import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 

        // Tanya apakah Pasien member
        System.out.print("Apakah Pasien member? (y/t): ");
        String isMember = input.nextLine().trim();

        boolean[] gejalaPasien = new boolean[Data.gejala.length];

        // Tanya pasien sebanyak data gejala
        for (int i = 0; i < Data.gejala.length; i++) {
            System.out.print("Apakah Pasien mengalami gejala " + Data.gejala[i] + " ? (y/t): ");
            gejalaPasien[i] = input.nextLine().equalsIgnoreCase("y");
        }

        // Cari diagnosa berdasarkan gejala
        String diagnosa = "Tidak Diketahui"; //set by default
        boolean semuaGejalaTrue = true;
        boolean semuaGejalaFalse = true;

        for (boolean gejala : gejalaPasien) {
            if (!gejala) {
                semuaGejalaTrue = false;
            } else {
                semuaGejalaFalse = false;
            }
        }

        // Logika seleksi gejala
        if (semuaGejalaFalse) {
            System.out.println("Pasien tidak sakit");
        } else if (!gejalaPasien[1] && !gejalaPasien[4] && !gejalaPasien[7]) {
            diagnosa = Data.penyakit[0];  // Masuk Angin
        } else if (semuaGejalaTrue) {
            diagnosa = Data.penyakit[1];  // Flu
        } else if (!gejalaPasien[1] && !gejalaPasien[7] && !gejalaPasien[10]) {
            diagnosa = Data.penyakit[2];  // Salesma
        } else if (gejalaPasien[0] && gejalaPasien[1] && gejalaPasien[7] && gejalaPasien[8]) {
            diagnosa = Data.penyakit[3];  // Alergi
        } //end


        // Output biaya && diagnosa
        System.out.println ("\n========= Rincian Biaya & Diagnosa =========");
        System.out.println("Diagnosa: " + diagnosa);

        // Logika beri obat sesuai penyakit
        String[] obatDiberikan = new String[0];
        switch (diagnosa) {
            case "Flu":
                obatDiberikan = Data.obatFlu; //ambil data obat sesuai penyakit dari Data.java
                break;
            case "Masuk Angin":
                obatDiberikan = Data.obatMasukAngin;
                break;
            case "Salesma":
                obatDiberikan = Data.obatSalesma;
                break;
            case "Alergi":
                obatDiberikan = Data.obatAlergi;
                break;
            default:
                obatDiberikan = new String[0];
        }
      
        // Hitung total biaya
        int totalBiaya = 0;
        int jasaPelayanan = diagnosa.equals("Tidak Diketahui") ? 0 : 150000;

        for (String obatItem : obatDiberikan) {
            String[] obatInfo = obatItem.split(" = ");
            int hargaObat = Integer.parseInt(obatInfo[1]);
            totalBiaya += hargaObat;
        }

        if (isMember.equalsIgnoreCase("y")) {
            totalBiaya -= 50000;
        }

        totalBiaya += jasaPelayanan;

        // Tampilkan Output obat dan total biaya jika diagnosa bukan "Anda tidak sakit"
        if (!diagnosa.equals("Pasien tidak sakit")) {
            System.out.println("Obat yang diberikan:");
            for (String obatItem : obatDiberikan) {
                System.out.println("Obat : " + obatItem);
            }
            System.out.println("Total bayar : " + totalBiaya);
        }
        input.close();
    }
} 
