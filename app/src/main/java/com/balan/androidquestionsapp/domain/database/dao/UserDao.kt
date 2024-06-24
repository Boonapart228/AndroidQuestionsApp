package com.balan.androidquestionsapp.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.balan.androidquestionsapp.domain.database.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email Limit 1")
    fun findUser(email: String): Boolean

    @Update(entity = UserEntity::class)
    fun updateScore(userEntity: UserEntity)

    @Insert
    fun createUser(userEntity: UserEntity)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :accountId")
    fun getUserById(accountId: Long): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun findUser(email: String, password: String): UserEntity?

    @Query("SELECT * FROM users ORDER BY name")
    fun sortUserByName(): List<UserEntity>

    @Query("SELECT * FROM users ORDER BY junior AND middle AND senior")
    fun sortByIncreasingScore(): List<UserEntity>

    @Query("SELECT * FROM users ORDER BY junior DESC, middle DESC, senior DESC")
    fun sortByDecreasingScore(): List<UserEntity>
}