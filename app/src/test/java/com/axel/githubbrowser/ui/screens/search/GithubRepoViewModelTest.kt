package com.axel.githubbrowser.ui.screens.search

import com.axel.githubbrowser.business.user.GetUserById
import com.axel.githubbrowser.business.user.SearchFilter
import com.axel.githubbrowser.business.user.SearchUsers
import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.models.view.ViewSearchResults
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GithubRepoViewModelTest {

  private val testDispatcher = StandardTestDispatcher()
  private lateinit var viewModel: GithubRepoViewModel

  private val searchUserUseCase = SearchUsers(FakeRepository())
  private val getUserByIdUseCase = Mockito.mock(GetUserById::class.java)

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
    viewModel = GithubRepoViewModel(searchUserUseCase, getUserByIdUseCase)
  }

  @Test
  fun `When intention is executed , state is changed`() = runBlocking {
    viewModel.executeIntention(SearchIntentions.SearchByName("testing"))
    testDispatcher.scheduler.advanceUntilIdle()
    val uiState = viewModel.uiState.value
    Truth.assertThat(uiState.viewSearchResults.totalCount).isEqualTo(10)
  }


}

