package com.example.core.domain

sealed class UIComponent {
    data class Dialog(
        val title: String,
        val description: String? = null,
    ) : UIComponent()
    data class None(
        val message: String
    ) : UIComponent()
//    Since UIComponent is a sealed class, all its subclasses must
//    explicitly inherit from it. This means every data class inside must extend (: UIComponent()).
//    Without : UIComponent(), Dialog would not be recognized as a valid
//    subclass of UIComponent, causing a compilation error.
}

