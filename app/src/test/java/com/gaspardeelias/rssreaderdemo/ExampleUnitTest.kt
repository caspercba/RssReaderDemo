package com.gaspardeelias.rssreaderdemo

import com.gaspardeelias.rssreaderdemo.repository.model.ResultWrapper
import com.gaspardeelias.rssreaderdemo.repository.safeApiCall
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okio.IOException
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Test
    fun `when lambda returns successfully then it should emit the result as success`() {
        runBlockingTest {
            val lambdaResult = true
            val result = safeApiCall(dispatcher) { lambdaResult }
            assertEquals(ResultWrapper.Success(lambdaResult), result)
        }
    }

    @Test
    fun `when lambda throws IOException then it should emit the result as NetworkError`() {
        runBlockingTest {
            val ioException = IOException()
            val result = safeApiCall(dispatcher) { throw ioException }
            assertEquals(ResultWrapper.GenericError(ioException), result)
        }
    }
}