package fr.deuspheara.potterdbapp

import fr.deuspheara.potterdbapp.data.network.model.*

object TestingModelProvider {

    fun provideCharacterResponse(): PotterResponse<CharacterType> {
        return PotterResponse(
            data = provideCharacterType(),

            meta = providePotterMeta(),
            links = PotterResponse.PotterLinks(self = "", current = "", next = "", last = "")
        )
    }

    fun provideMultipleCharacterResponse() : PotterResponse<List<CharacterType>>{
        return PotterResponse(
            data = listOf(
                provideCharacterTypeWithId("1"),
                provideCharacterTypeWithId("2"),
                provideCharacterTypeWithId("3"),
                provideCharacterTypeWithId("4"),
                provideCharacterTypeWithId("5"),
            ),
            meta = providePotterMeta(),
            links = PotterResponse.PotterLinks(self = "", current = "", next = "", last = "")
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

    fun providePotterLinks(): PotterResponse.PotterLinks {
        return PotterResponse.PotterLinks(
            self = "",
            current = "",
            next = null,
            last = ""
        )
    }

    fun providePotterMeta(): PotterResponse.PotterMeta {
        return PotterResponse.PotterMeta(
            pagination = PotterResponse.PotterMeta.PotterPagination(
                current = 0,
                next = 0,
                last = 0,
                records = 0
            ),
            copyright = "",
            generatedAt = ""
        )
    }

    fun providePotterPagination(): PotterResponse.PotterMeta.PotterPagination {
        return PotterResponse.PotterMeta.PotterPagination(
            current = 0,
            next = null,
            last = 0,
            records = 0
        )
    }


}
