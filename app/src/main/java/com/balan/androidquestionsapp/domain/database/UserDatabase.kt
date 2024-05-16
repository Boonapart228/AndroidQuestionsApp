package com.balan.androidquestionsapp.domain.database

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity(
    tableName = "users",
    indices = [
        Index("email", unique = true)
    ]
)
data class UserTDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val email: String,
    val password: String,
    val junior: Int,
    val middle: Int,
    val senior: Int,
) {
    fun toUser(): UserTuple = UserTuple(
        id = id,
        name = name,
        email = email,
        password = password,
        junior = junior,
        middle = middle,
        senior = senior
    )

}


data class UserTuple(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val junior: Int,
    val middle: Int,
    val senior: Int,
) {
    fun toEntity(): UserTDbEntity = UserTDbEntity(
        id = id,
        name = name,
        email = email,
        password = password,
        junior = junior,
        middle = middle,
        senior = senior
    )
}

@Database(
    version = 1,
    entities = [
        UserTDbEntity::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email Limit 1")
    fun findUser(email: String): UserTuple?

    @Update(entity = UserTDbEntity::class)
    fun updateScore(userTuple: UserTuple)

    @Insert
    fun createUser(userTDbEntity: UserTDbEntity)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserTDbEntity>

    @Query("SELECT * FROM users WHERE id = :accountId")
    fun getUserById(accountId: Long): UserTDbEntity?
}

interface UserLocalSource {
    fun getUserById(accountId: Long): UserTDbEntity?
    fun createUser(userTuple: UserTuple)

    fun getAllUsers(): List<UserTuple>

    fun updateScore(userTuple: UserTuple)
}

class UserLocalSourceImpl(
    private val userDao: UserDao
) : UserLocalSource {

    override fun getUserById(accountId: Long): UserTDbEntity? {
       return userDao.getUserById(accountId = accountId)
    }

    override fun updateScore(userTuple: UserTuple) {
        userDao.updateScore(userTuple = userTuple)
    }

    override fun createUser(userTuple: UserTuple) {
        userDao.createUser(userTDbEntity = userTuple.toEntity())
    }

    override fun getAllUsers(): List<UserTuple> {
        return userDao.getAllUsers().map { user -> user.toUser() }
    }
}