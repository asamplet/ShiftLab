package com.example.shiftlab.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.shiftlab.R
import com.example.shiftlab.databinding.FragmentLoginBinding
import com.example.shiftlab.presentation.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModel()
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = requireNotNull(_binding) { "binding can't be null" }

    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel.preStartScreen()
        viewModel.nameLiveData.observe(viewLifecycleOwner) {
            if (binding.nameField.editText?.text.toString() != it) {
                binding.nameField.editText?.setText(it)
            }
            binding.buttonReg.isEnabled = viewModel.fieldCheck()
            binding.nameField.error = ""
        }

        viewModel.surnameLiveData.observe(viewLifecycleOwner) {
            if (binding.surnameField.editText?.text.toString() != it) {
                binding.surnameField.editText?.setText(it)
            }
            binding.buttonReg.isEnabled = viewModel.fieldCheck()
            binding.surnameField.error = ""
        }

        viewModel.dateLiveData.observe(viewLifecycleOwner) {
            binding.dateField.text = it
            binding.buttonReg.isEnabled = viewModel.fieldCheck()
        }

        viewModel.passLiveData.observe(viewLifecycleOwner) {
            if (binding.passwordField.editText?.text.toString() != it) {
                binding.passwordField.editText?.setText(it)
            }
            binding.buttonReg.isEnabled = viewModel.fieldCheck()
            binding.passwordField.error = ""
        }

        viewModel.passRepLiveData.observe(viewLifecycleOwner) {
            if (binding.passwordRepeatField.editText?.text.toString() != it) {
                binding.passwordRepeatField.editText?.setText(it)
            }
            binding.buttonReg.isEnabled = viewModel.fieldCheck()
            binding.passwordRepeatField.error = ""
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            when(it){
                NAME_ERROR -> binding.nameField.error = getString(R.string.nameErrorText)
                SURNAME_ERROR -> binding.surnameField.error = getString(R.string.surnameErrorText)
                PASSWORD_ERROR -> binding.passwordField.error = getString(R.string.pasErrorText)
                PASSWORD_ERROR_REPEAT -> binding.passwordRepeatField.error = getString(R.string.passRepErrorText)
                CORRECT -> viewModel.navigateToMainScreen()
            }
        }
        binding.dateField.setOnClickListener {
            val dpd = DatePickerDialog(
                requireActivity(),
                { _, year, _, dayOfMonth ->
                    viewModel.dateLiveData.value = ("$dayOfMonth.$month.$year")
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        binding.buttonReg.setOnClickListener {
            viewModel.validCheck()
        }

        binding.nameField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.nameLiveData.value = text.toString()
        }

        binding.surnameField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.surnameLiveData.value = text.toString()
        }

        binding.passwordField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.passLiveData.value = text.toString()
        }

        binding.passwordRepeatField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.passRepLiveData.value = text.toString()
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}