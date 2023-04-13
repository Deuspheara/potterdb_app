package fr.deuspheara.potterdbapp

import androidx.paging.Pager
import fr.deuspheara.potterdbapp.data.network.model.*

object TestingModelProvider {

    fun provideCharacterResponse(): CharacterResponse {
        return CharacterResponse(
            data = listOf(
                provideCharacterType()
            ),
            meta = providePotterMeta(),
            links = PotterLinks(self = "", current = "",next= "", last = "")
        )
    }

    fun provideMultipleCharacterResponse() : CharacterResponse{
        return CharacterResponse(
            data = listOf(
                provideCharacterTypeWithId("1"),
                provideCharacterTypeWithId("2"),
                provideCharacterTypeWithId("3"),
                provideCharacterTypeWithId("4"),
                provideCharacterTypeWithId("5"),
            ),
            meta = providePotterMeta(),
            links = PotterLinks(self = "", current = "",next= "", last = "")
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
    fun providePotterCharacter(): PotterCharacter {
        return PotterCharacter(
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

    fun providePotterLinks(): PotterLinks {
        return PotterLinks(
            self = "",
            current = "",
            next = null,
            last = ""
        )
    }

    fun providePotterMeta(): PotterMeta {
        return PotterMeta(
            pagination = PotterPagination(current = 0, next = 0, last = 0, records = 0),
            copyright = "",
            generatedAt = ""
        )
    }

    fun providePotterPagination(): PotterPagination {
        return PotterPagination(
            current = 0,
            next = null,
            last = 0,
            records = 0
        )
    }

}
