package com.devpro.android58_day10

// Bài tập về nhà
// Bài 1: Compress chuỗi eg: "aaabbcaaa" -> "a3b2ca3", "abc" -> "abc", "aabbcc" -> "a2b2c2"
// Bài 2: Tính giai thừa của một số nguyên dương n (n!) = 1*2*3*...*n
// Bài 3: Tìm số lớn thứ nhì trong list, không sử dụng hàm có sẵn
// Bài 4: Tìm độ dài chuỗi liên tiếp tăng  dài nhất. eg: [1, 3, 5, 4, 7, 8, 9, 2] → 4 (chuỗi 4,7,8,9)
// Bài 5: Chuyển số La Mã thành số nguyên. eg: "XII" -> 12, "IX" -> 9, "LVIII" -> 58
fun main() {
    print("Bai 1:")
    compressString("aaabbcaaa")
    println("Bai 2: ${giaiThua(5)}")
    print("Bai 3:")
    timSoLonThuNhi(listOf(1, 3, 5, 4, 7, 8, 9, 2))
    print("Bai 4:")
    stringLongest(listOf(1, 3, 5, 4, 7, 8, 9, 2))
    print("Bai 5:")
    chuyenDoiSoLaMa("LVIII")

}

// bài 1
fun compressString(chuoi: String) {
    if (chuoi.isEmpty()) print("chuoi rong")
    var kq = ""
    var i = 1;
    val size = chuoi.length
    for (j in 1 until size) {
//        var a1 = chuoi[j]
//        var a0 = chuoi[j-1]
        if (chuoi[j] == chuoi[j - 1]) {
            i++
        } else {
            kq += chuoi[j - 1]
            if (i > 1) {
                kq += i
            }
            i = 1
        }
    }
    kq += chuoi[size - 1]
    if (i > 1) kq += i
    println(kq)
}

// bài 2
fun giaiThua(soNguyen: Int): Int {
    if (soNguyen < 0) {
        return -1
    }
    if (soNguyen == 1 || soNguyen == 0) {
        return 1
    }
    var gThua = 1
    for (i in 2..soNguyen) {
        gThua *= i
    }
    return gThua
}

// bài 3
fun timSoLonThuNhi(list: List<Int>) {
    if (list.isEmpty()) {
        print("list rong")
        return
    }
    var soLonNhat = Int.MIN_VALUE
    var soLonT2 = Int.MIN_VALUE
    for (i in 0 until list.size) {
        if (list[i] > soLonNhat) {
            soLonT2 = soLonNhat
            soLonNhat = list[i]
        } else if (list[i] > soLonT2 && list[i] != soLonNhat) {
            soLonT2 = list[i]
        }
    }
    println("so lon t2 la: $soLonT2")
}

// Bài 4: Tìm độ dài chuỗi liên tiếp tăng  dài nhất. eg: [1, 3, 5, 4, 7, 8, 9, 2] → 4 (chuỗi 4,7,8,9)
fun stringLongest(list: List<Int>) {
    if (list.isEmpty()) {
        println("danh sach khong hop le")
        return
    }
    var result = 1
    var dem = 1
    for (i in 1 until list.size) {
        if (list[i] > list[i - 1]) {
            dem++
        } else {
            if (result < dem) {
                result = dem
            }
            dem = 1
        }
    }
    if (result < dem) {
        result = dem
    }
    println(result)

}

// Bài 5: Chuyển số La Mã thành số nguyên. eg: "XII" -> 12, "IX" -> 9, "LVIV" -> 58
fun chuyenDoiSoLaMa(chuoi: String) {
    if (chuoi.isEmpty()) {
        print("chuoi rong")
        return
    }
    var result = 0
    for (i in 0 until chuoi.length) {
        var vTri1 = quyDoi(chuoi[i])
        if (i < chuoi.length - 1) {
            var vTri2 = quyDoi(chuoi[i+1])
            if (vTri1 < vTri2) {
                result -= vTri1
            } else {
                result += vTri1
            }
        } else{
            result += vTri1
        }
    }
//    var size = mangLaMa.size
//    if (mangLaMa[mangLaMa[size-1]] > mangLaMa[mangLaMa[size-1] - 1]) {
//        result += mangLaMa[mangLaMa.last()] - mangLaMa[mangLaMa.last() - 1]
//    } else {
//        result += mangLaMa[mangLaMa.last()]
//    }
    println(result)
}

fun quyDoi(kyTu: Char): Int = when (kyTu) {
    'I' -> 1
    'V' -> 5
    'X' -> 10
    'L' -> 50
    'C' -> 100
    'D' -> 500
    'M' -> 1000
    else -> 0
}