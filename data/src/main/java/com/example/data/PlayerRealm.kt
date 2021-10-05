package com.example.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class PlayerRealm constructor(
    @PrimaryKey var id: String = ObjectId().toHexString(),
    var name: String = "",
    var position: String = ""
) : RealmObject()
