package com.example.plasticmandi.db
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.plasticmandi.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setUser(user : User) : Long

    @Query("SELECT * FROM user")
    fun getUser() : LiveData<User>

    @Delete
    suspend fun deleteUser(user: User)
}