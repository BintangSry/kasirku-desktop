# 🛒 KasirKu: Modern Point of Sale System (Desktop)

[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/Database-MySQL-blue.svg)](https://www.mysql.com/)
[![FlatLaf](https://img.shields.io/badge/UI-FlatLaf-brightgreen.svg)](https://www.formdev.com/flatlaf/)
[![Standard](https://img.shields.io/badge/Architecture-Modular-red.svg)](#)

**KasirKu** adalah aplikasi kasir (Point of Sale) berbasis desktop yang dikembangkan dengan standar industri modern. Aplikasi ini dirancang untuk memberikan pengalaman transaksi ritel yang cepat, aman, dan cerdas melalui integrasi hardware scanner serta manajemen basis data yang efisien.

---

## 🚀 Fitur Unggulan

### 🏬 Sisi Kasir (User Experience)
- **Inovasi Barcode Scanner:** Integrasi langsung dengan webcam untuk input produk secara instan menggunakan teknologi ZXing.
- **Smart Real-time Cart:** Manajemen keranjang belanja cerdas dengan akumulasi kuantitas otomatis dan perhitungan total belanja secara presisi.
- **Live Smart Search:** Pencarian produk tanpa tombol (real-time filtering) menggunakan logika DocumentListener.
- **Dynamic Categories:** Navigasi cepat untuk memfilter menu berdasarkan kategori makanan atau minuman.

### 🛡️ Sisi Admin (Business Intelligence)
- **Advanced Dashboard:** Visualisasi data statistik pendapatan harian, jumlah transaksi, dan total pengguna dalam satu layar.
- **Inventory Control Alert:** Sistem deteksi otomatis untuk produk yang memiliki stok kritis (hampir habis).
- **Professional CRUD Management:** Pengelolaan data master (Menu & User) dengan fitur upload gambar dan preview visual.
- **Financial Reporting:** Pencetakan laporan riwayat penjualan lengkap ke format fisik (Printer) atau digital (PDF).
- **Multi-Role Authentication:** Keamanan data berbasis peran (Admin & Kasir) untuk menjaga integritas informasi keuangan.

---

## 📸 Tampilan Aplikasi

| Dashboard Kasir | Scan Barcode |
| :---: | :---: |
| ![Cashier Dashboard](https://github.com/BintangSry/kasirku-desktop/blob/main/Screenshots/dashboard-kasir.png) | ![Barcode Scan](https://github.com/BintangSry/kasirku-desktop/blob/main/Screenshots/barcode-scan.png) |

| Dashboard Admin | Laporan Penjualan |
| :---: | :---: |
| ![Admin Dashboard](https://github.com/BintangSry/kasirku-desktop/blob/main/Screenshots/dashboard-admin.png) | ![Report PDF](https://github.com/BintangSry/kasirku-desktop/blob/main/Screenshots/report.png) |

---

## 🛠️ Tech Stack & Libraries

Aplikasi ini dibangun menggunakan ekosistem library terbaik untuk memastikan stabilitas sistem:

| Library | Kegunaan |
| :--- | :--- |
| **FlatLaf 3.7** | Modern UI & High-DPI Scaling support |
| **Sarxos Webcam** | Hardware bridge untuk akses kamera webcam |
| **ZXing (Core/JavaSE)** | Engine pengolahan pola barcode kelas dunia |
| **MySQL Connector** | Konektivitas database berkecepatan tinggi |
| **BridJ 0.7.0** | Akses native OS untuk performa kamera rendah latensi |
| **Protobuf Java** | Serialisasi data terstruktur yang efisien |
| **RojeruSan** | Komponen UI Material Design |

---

## 📂 Struktur Project (Modular Architecture)

Proyek ini mengikuti standar **Modular Development** untuk memudahkan pemeliharaan kode:

```text
com.bintang.kasir
├── components    # Custom reusable UI components (Table, Panel, Dialog)
├── config        # Database connection & system configuration
├── menu          # Business logic for Menu management
├── model         # Data models (Entities)
├── ui            # Main Frames (Dashboard, Login, Admin Dashboard)
└── user          # Business logic for User management
```

## ⚙️ Cara Menjalankan Project

### 1. Persiapan Database:

* Aktifkan Apache & MySQL di XAMPP.
* Buat database baru bernama db_kasir.
* Import file .sql yang tersedia di folder database proyek ini.

### 2. Setup di IDE:

* Clone repository ini: git clone https://github.com/BintangSry/kasirku-desktop.git.
* Buka project melalui Apache NetBeans.
* Pastikan semua library di folder Dependencies sudah terdeteksi.

#### 3. Menjalankan Aplikasi

* Buka package `com.bintang.kasir.ui`.
* Klik kanan pada file `LoginForm.java` lalu pilih **Run File**.
* Gunakan *kredensial default* berikut untuk masuk ke dalam sistem:

| Role | Username | Password |
| :--- | :--- | :--- |
| 👑 **Admin** | `admin` | `admin` |
| 🛒 **Kasir** | `kasir` | `kasir` |

---

## 👨‍💻 Developer

**[Bintang Surya]** — *Software Developer & Fullstack Developer*

> *Project ini dibuat dengan penuh dedikasi sebagai portofolio pengembangan aplikasi desktop berstandar industri dan untuk memenuhi standar penilaian tingkat lanjut.*
