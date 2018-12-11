# Guest House
| Grup | 12 |
| :---------------: | :---------------:|
| Andi Novan Prastya | 02 |
| Aura Kanza Caesaria | 04 |

## Deskripsi
Guest House adalah aplikasi Android yang digunakan untuk memberikan informasi berupa video profil, lokasi dan keterangan detail dari guest house yang ada di kota malang

## Screenshots
+ Tampilan Login dan Register
![Login dan Register](https://i.ibb.co/D5rFz60/Screenshot-2018-12-11-22-48-56-349-com-example-andinovanprastya-loginfirebase.png)

+ Tampilan Home/ Halaman Utama
![Home](https://i.ibb.co/vJr2z9z/Screenshot-2018-12-11-22-51-05-129-com-example-andinovanprastya-loginfirebase.png)

+ Sidebar
![Drawer](https://i.ibb.co/Gx6ghxS/Screenshot-2018-12-11-22-51-07-504-com-example-andinovanprastya-loginfirebase.png)

+ List Guest House
![List Guest House](https://i.ibb.co/dfyKfXL/Screenshot-2018-12-11-22-51-10-376-com-example-andinovanprastya-loginfirebase.png)

+ Detail Guest House setelah dipilih
![Detail Guest House](https://i.ibb.co/0XXLtXc/Screenshot-2018-12-11-22-51-13-150-com-example-andinovanprastya-loginfirebase.png)

+ Tampilan Informasi berupa Lokasi dan Video Guest House
![Informasi](https://i.ibb.co/gTfTzYZ/Screenshot-2018-12-11-22-51-20-939-com-example-andinovanprastya-loginfirebase.png)

+ Tampilan pencarian lokasi
![Lokasi](https://i.ibb.co/t8Lw1cx/Screenshot-2018-12-11-22-51-24-254-com-example-andinovanprastya-loginfirebase.png)

+ Lokasi saat ini
![Lokasi2](https://i.ibb.co/tmyXsnh/Screenshot-2018-12-11-22-51-32-128-com-example-andinovanprastya-loginfirebase.png)

+ Media Playback menampilkan video Guest House
![ListVideo](https://i.ibb.co/bm8sfzw/Screenshot-2018-12-11-22-52-07-612-com-example-andinovanprastya-loginfirebase.png)

+ Menu Sign Out
![SignOut](https://i.ibb.co/YTc9F4K/Screenshot-2018-12-11-22-52-15-570-com-example-andinovanprastya-loginfirebase.png)

## Android OS dan Level
min Sdk version yang digunakan adalah 15, Android OS Ice Cream Sandwich

## List class
      1. KamarDetailFragment (fragment untuk detail kamar)
      2. KamarFrgament (fragment untuk jenis kamar)
      3. SignoutFragment (fragment untuk logout)
      4. InfromationFragment (fragment untuk menampilkan video dan location)
      
      5. DapatkanAlamatTask (class untuk menambahkan location)
      6. LocationActivity (untuk menampilkan location)

      7. BaseActivity (digunakan untuk menampilkan progres dialog)
      8. EmailPasswordActivity (digunakan untuk pengisian email dan password sebagai metode untuk login)
      9. LoginAcitivity (digunakan untuk proses login)
      10. RegisterActivity (digunakan untuk proses register akun baru)
      11. TokenBroadcastReceiver (digunakan untuk menerima token)
      
      12. Kamar (digunakan untuk menyimpan detail dari kamar)
      13. VideoModel () (digunakan untuk create object video)

      14. MyFirebaseInstanceService (digunakan untuk mendapatkan token baru ketika aplikasi di install ulang)
      15. MyFirebaseMessagingService (digunakan untuk menampilkan data pengirim dan menampilkan notifikasi dari data)
      16. MyNotificationManager (digunakan untuk mengatur ketika user mengeklik notifikasi maka akan diarahkan ke halaman main_activity)

      17. NewAppWidget () (digunakan unuk menampilkan gambar yang telah di simpan pada stackremoteviewsfactory)
      18. StackRemoteViewsFactory () (Untuk menampung data kita membutuhkan sebuah kelas implementasi dari RemoteViewsService.RemoteViewsFactory)
      19. StackWidgetService ()(sebuah class yg digunakan sebagai widget service)

      20. VideoActivity (digunakan untuk menampilkan video)
      21. DetailAcitivity (digunakan untuk menampilkan data detail berdasarkan id)
      22. MenuActivity (digunakan untuk menampilkan menu pada aplikasi)

## Referensi
*[Dicoding](https://www.dicoding.com/academies/14) - Tutorial Widget

## Lisensi
MIT License

Copyright (c) 2018 felixsoares

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.




Copyright 2016 Aditya Ladwa

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
