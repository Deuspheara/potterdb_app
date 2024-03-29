package fr.deuspheara.potterdbapp.data.network.mapper

import androidx.paging.PagingData
import androidx.paging.map
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<PagingData<CharacterType>>.toCharacterLight(
): Flow<PagingData<CharacterLightModel>> {
    return this.map { pagedData ->
        pagedData.map { characterPotter ->
            CharacterLightModel(
                slug = characterPotter.attributes.slug,
                name = characterPotter.attributes.name,
                image = characterPotter.attributes.image,
                species = characterPotter.attributes.species,
                gender = characterPotter.attributes.gender,
                house = characterPotter.attributes.house,
                born = characterPotter.attributes.born,
                height = characterPotter.attributes.height,
                weight = characterPotter.attributes.weight
            )
        }
    }
}

fun CharacterType.PotterCharacter.toCharacterLight(): CharacterLightModel {
    return CharacterLightModel(
        slug = this.slug,
        name = this.name,
        image = this.image,
        species = this.species,
        gender = this.gender,
        house = this.house,
        born = this.born,
        height = this.height,
        weight = this.weight
    )
}

fun CharacterType.toCharacterLight(): CharacterLightModel {
    return CharacterLightModel(
        slug = this.attributes.slug,
        name = this.attributes.name,
        image = this.attributes.image,
        species = this.attributes.species,
        gender = this.attributes.gender,
        house = this.attributes.house,
        born = this.attributes.born,
        height = this.attributes.height,
        weight = this.attributes.weight
    )
}