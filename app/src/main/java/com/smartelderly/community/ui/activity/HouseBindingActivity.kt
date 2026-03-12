package com.smartelderly.community.ui.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartelderly.community.R
import com.smartelderly.community.data.model.*
import com.smartelderly.community.viewmodel.HouseViewModel

/**
 * 房屋绑定Activity
 */
class HouseBindingActivity : AppCompatActivity() {

    private lateinit var viewModel: HouseViewModel

    private lateinit var spinnerCity: Spinner
    private lateinit var spinnerCommunity: Spinner
    private lateinit var spinnerComplex: Spinner
    private lateinit var spinnerBuilding: Spinner

    private lateinit var etUnit: EditText
    private lateinit var etFloor: EditText
    private lateinit var etRoomNumber: EditText

    private lateinit var btnSubmit: Button
    private lateinit var rvMyHouses: RecyclerView

    private var selectedCityId: Long? = null
    private var selectedCommunityId: Long? = null
    private var selectedComplexId: Long? = null
    private var selectedBuildingId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_binding)

        initViews()
        initViewModel()
        setupListeners()
        loadData()
    }

    private fun initViews() {
        spinnerCity = findViewById(R.id.spinner_city)
        spinnerCommunity = findViewById(R.id.spinner_community)
        spinnerComplex = findViewById(R.id.spinner_complex)
        spinnerBuilding = findViewById(R.id.spinner_building)

        etUnit = findViewById(R.id.et_unit)
        etFloor = findViewById(R.id.et_floor)
        etRoomNumber = findViewById(R.id.et_room_number)

        btnSubmit = findViewById(R.id.btn_submit)
        rvMyHouses = findViewById(R.id.rv_my_houses)

        rvMyHouses.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[HouseViewModel::class.java]

        // 观察城市列表
        viewModel.cityList.observe(this) { cities ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCity.adapter = adapter

            spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                    selectedCityId = cities[position].id
                    viewModel.loadCommunityList(selectedCityId ?: 0)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        // 观察社区列表
        viewModel.communityList.observe(this) { communities ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, communities)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCommunity.adapter = adapter

            spinnerCommunity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                    selectedCommunityId = communities[position].id
                    viewModel.loadComplexList(selectedCommunityId ?: 0)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        // 观察小区列表
        viewModel.complexList.observe(this) { complexes ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, complexes)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerComplex.adapter = adapter

            spinnerComplex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                    selectedComplexId = complexes[position].id
                    viewModel.loadBuildingList(selectedComplexId ?: 0)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        // 观察楼栋列表
        viewModel.buildingList.observe(this) { buildings ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, buildings)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerBuilding.adapter = adapter

            spinnerBuilding.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                    selectedBuildingId = buildings[position].id
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        // 观察我的房屋
        viewModel.myHouses.observe(this) { houses ->
            // TODO: 设置房屋列表适配器
            Toast.makeText(this, "我的房屋: ${houses.size}套", Toast.LENGTH_SHORT).show()
        }

        // 观察绑定成功
        viewModel.bindSuccess.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "申请已提交,等待审核", Toast.LENGTH_SHORT).show()
                viewModel.loadMyHouses()
            }
        }

        // 观察错误
        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupListeners() {
        btnSubmit.setOnClickListener {
            submitApplication()
        }
    }

    private fun loadData() {
        viewModel.loadCityList()
        viewModel.loadMyHouses()
    }

    private fun submitApplication() {
        val unit = etUnit.text.toString().trim()
        val floor = etFloor.text.toString().trim()
        val roomNumber = etRoomNumber.text.toString().trim()

        if (selectedBuildingId == null) {
            Toast.makeText(this, "请选择楼栋", Toast.LENGTH_SHORT).show()
            return
        }

        if (unit.isEmpty()) {
            Toast.makeText(this, "请输入单元号", Toast.LENGTH_SHORT).show()
            return
        }

        if (floor.isEmpty()) {
            Toast.makeText(this, "请输入楼层", Toast.LENGTH_SHORT).show()
            return
        }

        if (roomNumber.isEmpty()) {
            Toast.makeText(this, "请输入房号", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.applyBindHouse(
            buildingId = selectedBuildingId!!,
            roomNumber = roomNumber,
            floor = floor.toIntOrNull() ?: 0,
            unit = unit.toIntOrNull() ?: 0
        )
    }
}
