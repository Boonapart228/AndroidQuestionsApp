package com.balan.androidquestionsapp.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.balan.androidquestionsapp.domain.database.UserDbEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email Limit 1")
    fun findUser(email: String): UserDbEntity?

    @Update(entity = UserDbEntity::class)
    fun updateScore(userDbEntity: UserDbEntity)

    @Insert
    fun createUser(userDbEntity: UserDbEntity)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserDbEntity>

    @Query("SELECT * FROM users WHERE id = :accountId")
    fun getUserById(accountId: Long): UserDbEntity?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun findUser(email: String, password: String): UserDbEntity?

    @Query("SELECT * FROM users ORDER BY name")
    fun sortUserByName(): List<UserDbEntity>

    @Query("SELECT * FROM users ORDER BY junior AND middle AND senior")
    fun sortByIncreasingScore(): List<UserDbEntity>

    @Query("SELECT * FROM users ORDER BY junior DESC, middle DESC, senior DESC")
    fun sortByDecreasingScore(): List<UserDbEntity>
}