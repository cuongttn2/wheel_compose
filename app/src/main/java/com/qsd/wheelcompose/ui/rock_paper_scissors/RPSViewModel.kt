package com.qsd.wheelcompose.ui.rock_paper_scissors

import androidx.lifecycle.viewModelScope
import com.qsd.wheelcompose.base.BaseViewModel
import com.qsd.wheelcompose.ui.rock_paper_scissors.RPSIntent.HideResultDialog
import com.qsd.wheelcompose.ui.rock_paper_scissors.RPSIntent.HideFirework
import com.qsd.wheelcompose.ui.rock_paper_scissors.RPSIntent.NameP1Change
import com.qsd.wheelcompose.ui.rock_paper_scissors.RPSIntent.NameP2Change
import com.qsd.wheelcompose.ui.rock_paper_scissors.RPSIntent.StartPlay
import com.qsd.wheelcompose.utils.Utils.getGameResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RPSViewModel @Inject constructor() : BaseViewModel() {
    private val _state = MutableStateFlow<UIStateRPS>(UIStateRPS())
    val state: StateFlow<UIStateRPS> = _state

    fun handleIntent(intent: RPSIntent) {
        when (intent) {
            is StartPlay -> {
                playGame()
            }

            is NameP1Change -> {
                updateNameP1(intent.name)
            }

            is NameP2Change -> {
                updateNameP2(intent.name)
            }

            is HideFirework -> {
                hideFireWork()
            }

            is HideResultDialog -> {
                hideDialogResult()
            }

        }
    }

    private fun updateNameP1(name: String) {
        _state.update { it.copy(nameP1 = name) }
    }

    private fun updateNameP2(name: String) {
        _state.update { it.copy(nameP2 = name) }
    }

    private fun playGame() {
        if (_state.value.isPlaying) return
        viewModelScope.launch(Dispatchers.Main) {
            _state.update {
                it.copy(
                    isPlaying = true,
                    resultName = null,
                    playFirework = false
                )
            }
            //  playing game
            val startTime = System.currentTimeMillis()
            while (System.currentTimeMillis() - startTime < 3000) {
                // Mỗi 300ms, đổi lựa chọn để tạo cảm giác “quay”
                _state.update {
                    it.copy(
                        currentIndexP1 = (it.currentIndexP1 + 1) % it.rpsList.size,
                        currentIndexP2 = (it.currentIndexP2 + 1) % it.rpsList.size,
                    )
                }
                delay(300)
            }

            // Sau khi quay xong, random ra lựa chọn cuối
            _state.update {
                it.copy(
                    finalChoiceP1 = it.rpsList.random(),
                    finalChoiceP2 = it.rpsList.random(),
                )
            }

            // Tính kết quả
            _state.update {
                it.copy(
                    isPlaying = false,
                    playFirework = true,
                    showResult = true,
                    resultName = getGameResult(
                        it.finalChoiceP1,
                        it.finalChoiceP2,
                        it.nameP1,
                        it.nameP2
                    )
                )
            }
        }
    }

    private fun hideFireWork() {
        _state.update { it.copy(playFirework = false) }
    }

    private fun hideDialogResult() {
        _state.update { it.copy(showResult = false) }
    }

}