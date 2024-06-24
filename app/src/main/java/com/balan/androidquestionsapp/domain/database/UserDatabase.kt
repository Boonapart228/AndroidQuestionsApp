package com.balan.androidquestionsapp.domain.database

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.balan.androidquestionsapp.domain.database.dao.UserDao
import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.User

@Entity(
    tableName = "users",
    indices = [
        Index("email", unique = true)
    ]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val email: String,
    val password: String,
    val junior: Int?,
    val middle: Int?,
    val senior: Int?,
)

fun UserEntity.toUser() = User(
    id = id,
    name = name,
    email = email,
    password = password,
    question = QuestionsScore(junior = junior, middle = middle, senior = senior)
)

fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    email = email,
    password = password,
    junior = question.junior,
    middle = question.middle,
    senior = question.senior,
)

@Database(
    version = 1,
    entities = [
        UserEntity::class
    ]
)

abstract class AppDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}




