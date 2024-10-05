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

    @Query("SELECT * FROM users WHERE email = :email")
    fun getByEmail(email: String): UserEntity?

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

    @Query(
        """
        SELECT * FROM users
        ORDER BY
        CASE
            WHEN name GLOB '[0-9]*' THEN 1
            ELSE 0
        END, 
        LOWER(name) ASC
    """
    )
    fun getUsersSortedByName(): List<UserEntity>

    @Query("SELECT * FROM users ORDER BY CASE WHEN :ascending THEN junior ELSE NULL END ASC, CASE WHEN :ascending THEN middle ELSE NULL END ASC, CASE WHEN :ascending THEN senior ELSE NULL END ASC, CASE WHEN :ascending THEN NULL ELSE junior END DESC, CASE WHEN :ascending THEN NULL ELSE middle END DESC, CASE WHEN :ascending THEN NULL ELSE senior END DESC")
    fun getUsersSortedByScore(ascending: Boolean): List<UserEntity>
}