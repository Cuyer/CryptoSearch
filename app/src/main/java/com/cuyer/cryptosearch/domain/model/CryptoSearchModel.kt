package com.cuyer.cryptosearch.domain.model


// Tworzymy klasę CryptoSearchModel w Domain, ponieważ chcemy, by nasze dane były niezależne od
// bibliotek zewnętrznych. Możemy dzięki temu w każdej chwili zmienić biblioteki z warstwy Data.
data class CryptoSearchModel(
    val name: String,
    val symbol: String,
    val is_new: String,
    val is_active: String,
)
