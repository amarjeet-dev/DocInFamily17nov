package com.e.docinfamily17nov.Model

class MedicalOptionModel(
    internal var title: String,
    internal var imagef: Int,
    internal var imageback: Int
) {
    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getImagef(): Int {
        return imagef
    }

    fun setImagef(imagef: Int) {
        this.imagef = imagef
    }

    fun getImageback(): Int {
        return imageback
    }

    fun setImageback(imageback: Int) {
        this.imageback = imageback
    }
}