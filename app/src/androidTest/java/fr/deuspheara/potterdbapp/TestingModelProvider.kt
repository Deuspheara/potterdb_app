package fr.deuspheara.potterdbapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import fr.deuspheara.potterdbapp.data.network.model.*
import fr.deuspheara.potterdbapp.data.paging.CharacterPagingSourceFake
import kotlinx.coroutines.runBlocking

object TestingModelProvider {

    fun provideCharacterResponse(): CharacterResponse<CharacterType> {
        return CharacterResponse(
            data = provideCharacterType(),

            meta = providePotterMeta(),
            links = CharacterResponse.PotterLinks(self = "", current = "", next = "", last = "")
        )
    }

    fun provideMultipleCharacterResponse() : CharacterResponse<List<CharacterType>>{
        return CharacterResponse(
            data = listOf(
                provideCharacterTypeWithId("1"),
                provideCharacterTypeWithId("2"),
                provideCharacterTypeWithId("3"),
                provideCharacterTypeWithId("4"),
                provideCharacterTypeWithId("5"),
            ),
            meta = providePotterMeta(),
            links = CharacterResponse.PotterLinks(self = "", current = "", next = "", last = "")
        )
    }

    fun provideCharacterType(): CharacterType {
        return CharacterType(
            id = "",
            type = "",
            attributes = providePotterCharacter()
        )
    }

    fun provideCharacterTypeWithId(id : String) : CharacterType{
        return CharacterType(
            id = id,
            type = "",
            attributes = providePotterCharacter()
        )
    }
    fun providePotterCharacter(): CharacterType.PotterCharacter {
        return CharacterType.PotterCharacter(
            slug = null,
            name = "Harry",
            born = null,
            died = null,
            gender = null,
            species = null,
            height = null,
            weight = null,
            hairColor = null,
            eyeColor = null,
            skinColor = null,
            bloodStatus = null,
            nationality = null,
            animagus = null,
            boggart = null,
            house = null,
            patronus = null,
            aliasNames = null,
            familyMembers = null,
            jobs = null,
            romances = null,
            titles = null,
            wands = null,
            image = null,
            wiki = null
        )
    }

    fun providePotterLinks(): CharacterResponse.PotterLinks {
        return CharacterResponse.PotterLinks(
            self = "",
            current = "",
            next = null,
            last = ""
        )
    }

    fun providePotterMeta(): CharacterResponse.PotterMeta {
        return CharacterResponse.PotterMeta(
            pagination = CharacterResponse.PotterMeta.PotterPagination(
                current = 0,
                next = 0,
                last = 0,
                records = 0
            ),
            copyright = "",
            generatedAt = ""
        )
    }

    fun providePotterPagination(): CharacterResponse.PotterMeta.PotterPagination {
        return CharacterResponse.PotterMeta.PotterPagination(
            current = 0,
            next = null,
            last = 0,
            records = 0
        )
    }


}
