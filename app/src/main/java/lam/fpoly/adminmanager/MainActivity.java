package lam.fpoly.adminmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.learnoset.material.ui.learnosetnavigationbar.CustomNavTheme;
import com.learnoset.material.ui.learnosetnavigationbar.LearnosetNavItem;
import com.learnoset.material.ui.learnosetnavigationbar.LearnosetNavigationBar;
import com.learnoset.material.ui.learnosetnavigationbar.NavItemsGroup;
import com.learnoset.material.ui.learnosetnavigationbar.NavigationEventListener;


import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.Dao.TbDanhMucDao;
import lam.fpoly.adminmanager.Dao.TbSanPhamDao;
import lam.fpoly.adminmanager.Fragment.QuanLyKhuyenMai;

import lam.fpoly.adminmanager.Fragment.QuanLyDonHang;
import lam.fpoly.adminmanager.Fragment.QuanLyKhachHang;

import lam.fpoly.adminmanager.Fragment.QuanLySanPham;
import lam.fpoly.adminmanager.Model.TbDanhMuc;
import lam.fpoly.adminmanager.Model.TbSanPham;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout drawerLayout;
    private LearnosetNavigationBar learnosetNavigationBar;
    private boolean checkColor = true;
    private Toolbar toolbar;
    private TextView mTitle;
    private AppBarLayout actionBar;
    int loaiisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.tbToolBar);

        setSupportActionBar(toolbar);//add toolbar vào ứng dụng
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FAE8E0")));
        mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Quản lý sản phẩm");
        mTitle.setTextColor(Color.parseColor("#A45C40"));

        //thêm thanh 3 gạch vào toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,R.string.opendrawer,R.string.closedrawer);
        toggle.syncState();

        learnosetNavigationBar = findViewById(R.id.navigatioNabr);
        learnosetNavigationBar.setDrawerLayout(drawerLayout, LearnosetNavigationBar.DrawerGravity.LEFT);

        settingCustomIcons();
        settingCustomGroup();
        settingCustomColorLight();
        settingProfile();

        learnosetNavigationBar.setEventListener(new NavigationEventListener() {
            @Override
            public void onItemSelected(int position, LearnosetNavItem selectedNavItem) {
                switch (position){
                    case 0:
                        selectedNavItem.setFragment(new QuanLySanPham(),R.id.fragmentContainer);
                        mTitle.setText("Quản lý sản phẩm");
                        mTitle.setTextColor(Color.parseColor("#A45C40"));
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FAE8E0")));
                        break;
                    case 1:
                        //Quản lý đơn hàng
                        //set Fragment tương ứng vào đây
                        //selectedNavItem.setFragment(new 'Tên Fragment',R.id.fragmentContainer);
                        selectedNavItem.setFragment(new QuanLyDonHang(),R.id.fragmentContainer);
                        mTitle.setText("Quản lý đơn hàng");
                        mTitle.setTextColor(Color.parseColor("#A45C40"));
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FAE8E0")));
                        break;
                    case 2:
                        //Quản lý khuyến mại
                        //set Fragment tương ứng vào đây
                        selectedNavItem.setFragment(new QuanLyKhuyenMai(),R.id.fragmentContainer);
                        mTitle.setText("Quản lý khuyến mại");
                        mTitle.setTextColor(Color.parseColor("#A45C40"));
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FAE8E0")));
                        break;
                    case 3:
                        //Quản lý khách hàng
                        //set Fragment tương ứng vào đây
                        selectedNavItem.setFragment(new QuanLyKhachHang(),R.id.fragmentContainer);
                        mTitle.setText("Quản lý khách hàng");
                        mTitle.setTextColor(Color.parseColor("#A45C40"));
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FAE8E0")));
                        break;
                    case 4:
                        //Quản lý đánh giá
                        //set Fragment tương ứng vào đây
                        //selectedNavItem.setFragment(new 'Tên Fragment',R.id.fragmentContainer);
                        mTitle.setText("FeedBack");
                        mTitle.setTextColor(Color.WHITE);
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#385C9A")));
                        break;
                    case 5:
                        //Quản lý tài khoản
                        //set Fragment tương ứng vào đây
                        //selectedNavItem.setFragment(new 'Tên Fragment',R.id.fragmentContainer);
                        mTitle.setText("Quản lý tài khoản");
                        mTitle.setTextColor(Color.WHITE);
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C5270")));
                        break;
                    case 6:
                        //Thống kê
                        //set Fragment tương ứng vào đây
                        //selectedNavItem.setFragment(new 'Tên Fragment',R.id.fragmentContainer);
                        mTitle.setText("Thống kê");
                        mTitle.setTextColor(Color.WHITE);
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#145DA0")));
                        break;
                    case 7:
                        if(checkColor){
                            settingCustomColorDark();
                        }else {
                            settingCustomColorLight();
                        }
                        break;
                }
            }

            @Override
            public void onLogOutBtnClick() {
                //Khoi tao lai Activity main
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });

    }

    private void settingCustomColorDark(){
        CustomNavTheme customNavTheme = new CustomNavTheme();
        customNavTheme.setIconsColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setNavigationBackground(Color.BLACK);
        customNavTheme.setSelectedItemBackgroundColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setSelectedItemIconColor(Color.WHITE);
        customNavTheme.setSelectedItemTextColor(Color.WHITE);
        customNavTheme.setTextColor(Color.WHITE);
        customNavTheme.setGroupNameColor(Color.WHITE);

        learnosetNavigationBar.setTheme(customNavTheme);

        learnosetNavigationBar.setSelectedItemBackground(LearnosetNavigationBar.NavColors.ORANGE);
        learnosetNavigationBar.setIconsColor(LearnosetNavigationBar.NavColors.ORANGE);
        checkColor = false;
    }

    private void settingCustomColorLight(){
        CustomNavTheme customNavTheme = new CustomNavTheme();
        customNavTheme.setIconsColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setNavigationBackground(Color.WHITE);
        customNavTheme.setSelectedItemBackgroundColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setSelectedItemIconColor(Color.WHITE);
        customNavTheme.setSelectedItemTextColor(Color.WHITE);
        customNavTheme.setTextColor(Color.GRAY);

        learnosetNavigationBar.setTheme(customNavTheme);

        learnosetNavigationBar.setSelectedItemBackground(LearnosetNavigationBar.NavColors.DARK_BLUE);
        learnosetNavigationBar.setIconsColor(LearnosetNavigationBar.NavColors.BLACK);
        checkColor = true;
    }

    private void settingCustomIcons(){
        LearnosetNavItem customItem1 = new LearnosetNavItem();
        customItem1.setTitle("Quản lý sản phẩm");
        customItem1.setIcon(R.drawable.product_list);
        customItem1.setSelected(true);
        customItem1.setFragment(new QuanLySanPham(),R.id.fragmentContainer);

        LearnosetNavItem customItem2 = new LearnosetNavItem("Quản lý đơn hàng", R.drawable.document_management);
        LearnosetNavItem customItem3 = new LearnosetNavItem("Quản lý khuyến mại", R.drawable.cart_sale);
        LearnosetNavItem customItem4 = new LearnosetNavItem("Quản lý khách hàng", R.drawable.human_resoruces_outline);
        learnosetNavigationBar.addNavItem(customItem1);
        learnosetNavigationBar.addNavItem(customItem2);
        learnosetNavigationBar.addNavItem(customItem3);
        learnosetNavigationBar.addNavItem(customItem4);
    }

    private void settingCustomGroup(){

        NavItemsGroup navItemsGroup3 = new NavItemsGroup("FeedBack");
        navItemsGroup3.addGroupItem(LearnosetNavItem.BuiltInItems.FEEDBACK);
        learnosetNavigationBar.addItemsGroup(navItemsGroup3);

        NavItemsGroup navItemsGroup = new NavItemsGroup("Account & Statistical");
        navItemsGroup.addGroupItem(new LearnosetNavItem("Quản lý tài khoản", R.drawable.account_circle_outline));
        LearnosetNavItem customGroupItem = new LearnosetNavItem("Thống kê", R.drawable.clipboard_data);
        navItemsGroup.addGroupItem(customGroupItem);
        learnosetNavigationBar.addItemsGroup(navItemsGroup);

        NavItemsGroup navItemsGroup2 = new NavItemsGroup("Color Menu");
        navItemsGroup2.addGroupItem(LearnosetNavItem.BuiltInItems.TOOLS);
        learnosetNavigationBar.addItemsGroup(navItemsGroup2);
    }

    private void settingProfile(){
//        Bundle bundle = getIntent().getExtras();
//        User user = (User) bundle.get("user2");
//
//        File file = new File("You Image File Path");
//        try {
//            learnosetNavigationBar.setHeaderData(user.getName(),file);
//        } catch (LearnosetExceptions learnosetExceptions) {
//            learnosetExceptions.printStackTrace();
//        }
//        learnosetNavigationBar.setHeaderData(user.getName());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        QuanLySanPham quanLySanPham = new QuanLySanPham();
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }else if(item.getItemId() == R.id.themhang){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.themsanpham_dialog);
            Spinner spn = dialog.findViewById(R.id.spnLoaisp_addsp);
            EditText anh = dialog.findViewById(R.id.anh_addsp);
            EditText tensp = dialog.findViewById(R.id.tensp_addsp);
            EditText gianhap = dialog.findViewById(R.id.gianhap_addsp);
            EditText giaban = dialog.findViewById(R.id.giaban_addsp);
            EditText tonkho = dialog.findViewById(R.id.tonkho_addsp);
            Button cancel = dialog.findViewById(R.id.addsp_btnCCancel);
            Button add = dialog.findViewById(R.id.addsp_btnAdd);


            TbDanhMucDao tbDanhMucDao = new TbDanhMucDao();
            TbSanPhamDao tbSanPhamDao = new TbSanPhamDao();

            List<String> listSpn = new ArrayList<>();
            List<TbDanhMuc> danhMucList = tbDanhMucDao.getAll();
            for(TbDanhMuc tbdm : danhMucList){
                listSpn.add(tbdm.getTen_danhMuc());
            }
            ArrayAdapter spnAdapter = new ArrayAdapter(dialog.getContext(), android.R.layout.simple_spinner_item, listSpn);
            spn.setAdapter(spnAdapter);

            spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    loaiisp = tbDanhMucDao.getID(listSpn.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String anhsp = anh.getText().toString();
                    String ten = tensp.getText().toString();
                    int gia_nhap = Integer.parseInt(gianhap.getText().toString());
                    int gia_ban = Integer.parseInt(giaban.getText().toString());
                    int ton_kho = Integer.parseInt(tonkho.getText().toString());


                    if(anhsp.length() == 0 || ten.length() == 0 || gianhap.getText().toString().length() == 0 || giaban.getText().toString().length() == 0 || giaban.getText().toString().length() == 0){
                        Toast.makeText(MainActivity.this, "Phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    }else{
                        TbSanPham obj = new TbSanPham(ten,anhsp,gia_nhap,gia_ban,ton_kho,loaiisp);
                        tbSanPhamDao.insertRow(obj);
                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                }
            });
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}