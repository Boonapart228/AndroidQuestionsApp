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
import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.User

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
    val junior: Int?,
    val middle: Int?,
    val senior: Int?,
)

fun UserTDbEntity.toUser() = User(
    id = id,
    name = name,
    email = email,
    password = password,
    question = QuestionsScore(junior = junior, middle = middle, senior = senior)
)

fun User.toEntity() = UserTDbEntity(
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
        UserTDbEntity::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email Limit 1")
    fun findUser(email: String): UserTDbEntity?

    @Update(entity = UserTDbEntity::class)
    fun updateScore(userTDbEntity: UserTDbEntity)

    @Insert
    fun createUser(userTDbEntity: UserTDbEntity)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserTDbEntity>

    @Query("SELECT * FROM users WHERE id = :accountId")
    fun getUserById(accountId: Long): UserTDbEntity?
}

interface UserLocalSource {
    fun getUserById(accountId: Long): User?
    fun createUser(user: User)

    fun getAllUsers(): List<User>

    fun updateScore(user: User)
}

class UserLocalSourceImpl(
    private val userDao: UserDao
) : UserLocalSource {

    override fun getUserById(accountId: Long): User? {
        return userDao.getUserById(accountId = accountId)?.toUser()
    }

    override fun updateScore(user: User) {
        userDao.updateScore(userTDbEntity = user.toEntity())
    }

    override fun createUser(user: User) {
        userDao.createUser(userTDbEntity = user.toEntity())
    }

    override fun getAllUsers(): List<User> {
        return userDao.getAllUsers().map { userDbEntity -> userDbEntity.toUser() }
    }
}