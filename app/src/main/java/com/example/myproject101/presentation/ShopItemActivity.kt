package com.example.myproject101.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myproject101.R
import com.example.myproject101.databinding.ActivityShopItemBinding
import com.example.myproject101.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {
    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var edName: EditText
    private lateinit var edCount: EditText
    private lateinit var buttonSave: Button

    private val viewModel: ShopItemViewModel by viewModels()
    private val binding by lazy {
        ActivityShopItemBinding.inflate(layoutInflater)
    }
    private var screenMode = UNKNOWN_MODE
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        parseIntent()
        initViews()
        addTextChangeListener()
        launchRightMode()
        observeViewModel()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputCount.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            tilCount.error = message
        }
        viewModel.errorInputName.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            tilName.error = message
        }
        viewModel.shouldCloseScreen.observe(this) {
            finish()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListener() {
        edName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int,
            ) {
                viewModel.resetErrorName()
            }
        })

        edCount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int,
            ) {
                viewModel.resetInputCount()
            }
        })
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(this) {
            edName.setText(it.name)
            edCount.setText(it.count.toString())
        }
        buttonSave.setOnClickListener {
            viewModel.editShopItem(edName.text?.toString(), edCount.text?.toString())
        }

    }

    private fun launchAddMode() {
        buttonSave.setOnClickListener {
            viewModel.addShopItem(
                edName.text?.toString(),
                edCount.text?.toString()
            )
        }
    }

    private fun initViews() {
            tilName = binding.tilName
            tilCount = binding.tilCount
            edName = binding.edName
            edCount = binding.edCount
            buttonSave = binding.button
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val UNKNOWN_MODE = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent ")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent ")
            }
            shopItemId = intent.getIntExtra(
                EXTRA_SHOP_ITEM_ID,
                ShopItem.UNDEFINED_ID
            )
        }
    }
}