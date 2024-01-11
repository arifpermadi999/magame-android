package com.dicoding.core

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.dicoding.core.data.collector.ResultBound
import com.dicoding.core.data.source.local.DatabaseGame
import com.dicoding.core.data.source.local.entity.favorite.FavoriteDao
import com.dicoding.core.data.source.local.entity.game.GameDao
import com.dicoding.core.data.source.local.entity.game.GameEntity
import com.dicoding.core.data.source.local.source.GameLocalSource
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.network.ApiService
import com.dicoding.core.data.source.remote.response.DetailGameResponse
import com.dicoding.core.data.source.remote.source.GameRemoteSource
import com.dicoding.core.data.source.repository.GameRepository
import com.dicoding.core.domain.models.Game
import com.dicoding.core.domain.repository.IGameRepository
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.koin.test.inject
import org.mockito.Mockito.mock
import retrofit2.Retrofit
import timber.log.Timber
import kotlin.test.assertEquals


@ExperimentalCoroutinesApi
class KoinModuleTest : AutoCloseKoinTest() {

    //======= DATABASE ==========
    private val mockDatabaseGame: DatabaseGame = mock()

    private val gameDao: GameDao by inject()
    private val favoriteDao: FavoriteDao by inject()
    private val database: DatabaseGame by inject()

    //=============================
    //======= NETWORK ==========
    private val mockOkHttpClient: OkHttpClient = mock()
    private val mockRetrofit: Retrofit = mock()
    private val apiService: ApiService by inject()
    //=============================

    //======= Repository ==========
    private val mockLocalSource: GameLocalSource = mock()
    private val mockRemoteSource: GameRemoteSource = mock()
    private val mockRepository: GameRepository = mock()
    private val gameLocalSource: GameLocalSource by inject()
    private val gameRemoteSource: GameRemoteSource by inject()
    private val repository: GameRepository by inject()
    //=============================

    private val mockedContext: Context = mock(Context::class.java)



    private val moduleDatabase =
        module {
            single { mockDatabaseGame }
            factory { get<DatabaseGame>().gameDao() }
            factory { get<DatabaseGame>().favoriteDao() }
            single {
                Room.inMemoryDatabaseBuilder(
                    androidContext(),
                    DatabaseGame::class.java
                ).fallbackToDestructiveMigration()
                    .build()
            }
    }
    private val moduleNetwork = module {
        single { mockOkHttpClient }
        single { mockRetrofit }
        single { mock<ApiService>() }
    }
    private val moduleRepository = module{
        single { mockLocalSource }
        single { mockRemoteSource }
        single{ mockRepository }
    }
    @Test
    fun `test android context`() {
        startKoin {
            androidContext(mockedContext)
            modules(
                module {
                    single { mockDatabaseGame }
                }
            )
        }
        assertNotNull(mockDatabaseGame)
    }

    @Test
    fun `test database module dependencies`() {
        startKoin {
            androidContext(mockedContext)
            modules(
                moduleDatabase
            )
        }
        assertNotNull(gameDao)
        assertNotNull(favoriteDao)
        assertNotNull(database)
        assert(database is DatabaseGame)
    }

    @Test
    fun `test network module dependencies`() {
        startKoin {
            modules(
                moduleNetwork
            )
        }
        val injectedOkHttpClient: OkHttpClient by inject()
        val injectedRetrofit: Retrofit by inject()
        assertNotNull(injectedOkHttpClient)
        assertNotNull(injectedRetrofit)
        assert(apiService is ApiService)
    }

    @Test
    fun `test repository module dependencies`() {
        startKoin {
            androidContext(mockedContext)
            modules(
                moduleDatabase,
                moduleNetwork,
                moduleRepository
            )
        }
        assertNotNull(gameLocalSource)
        assertNotNull(gameRemoteSource)
        assertNotNull(repository)
        assert(repository is GameRepository)
    }


}



