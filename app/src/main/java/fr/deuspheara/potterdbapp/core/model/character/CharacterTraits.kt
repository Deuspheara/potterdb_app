package fr.deuspheara.potterdbapp.core.model.character

import fr.deuspheara.potterdbapp.R


data class CharacterTraits(
    val characteristics: Characteristics,
    val value: String
)

enum class Characteristics(val iconResId: Int) {
    GENDER(R.drawable.ic_gender),
    SPECIES(R.drawable.ic_species),
    HEIGHT(R.drawable.ic_height),
    WEIGHT(R.drawable.ic_weight),
    HOUSE(R.drawable.ic_house);
}