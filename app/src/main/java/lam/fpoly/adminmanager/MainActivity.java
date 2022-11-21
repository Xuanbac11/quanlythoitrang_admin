package lam.fpoly.adminmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.learnoset.material.ui.learnosetnavigationbar.CustomNavTheme;
import com.learnoset.material.ui.learnosetnavigationbar.LearnosetNavItem;
import com.learnoset.material.ui.learnosetnavigationbar.LearnosetNavigationBar;
import com.learnoset.material.ui.learnosetnavigationbar.NavItemsGroup;
import com.learnoset.material.ui.learnosetnavigationbar.NavigationEventListener;

import lam.fpoly.adminmanager.Fragment.QuanLySanPham;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout drawerLayout;
    private LearnosetNavigationBar learnosetNavigationBar;
    private boolean checkColor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable drawable= getResources().getDrawable(R.drawable.ic_baseline_menu_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        drawerLayout = findViewById(R.id.drawerLayout);
        learnosetNavigationBar = findViewById(R.id.navigatioNabr);

        learnosetNavigationBar.setDrawerLayout(drawerLayout, LearnosetNavigationBar.DrawerGravity.LEFT);

        settingCustomIcons();
        settingCustomGroup();
        settingCustomColorLight();

        learnosetNavigationBar.setEventListener(new NavigationEventListener() {
            @Override
            public void onItemSelected(int position, LearnosetNavItem selectedNavItem) {
                switch (position){
                    case 0:
                        selectedNavItem.setFragment(new QuanLySanPham(),R.id.fragmentContainer);
                        getSupportActionBar().setTitle("Quản lý sản phẩm");
                        break;
                    case 1:
                        //Quản lý đơn hàng
                        getSupportActionBar().setTitle("Quản lý đơn hàng");
                        break;
                    case 2:
                        //Quản lý khuyến mại
                        getSupportActionBar().setTitle("Quản lý khuyến mại");
                        break;
                    case 3:
                        //Quản lý khách hàng
                        getSupportActionBar().setTitle("Quản lý khách hàng");
                        break;
                    case 4:
                        //Quản lý đánh giá
                        getSupportActionBar().setTitle("Feedback");
                        break;
                    case 5:
                        //Quản lý tài khoản
                        getSupportActionBar().setTitle("Quản lý tài khoản");
                        break;
                    case 6:
                        //Thống kê
                        getSupportActionBar().setTitle("Thống kê");
                        break;
                    case 7:
                        if(checkColor == false){
                            settingCustomColorLight();
                        }else {
                            settingCustomColorDark();
                        }
                        break;
                }
            }

            @Override
            public void onLogOutBtnClick() {

            }
        });

        settingProfile();

    }

    private void settingCustomColorDark(){
        CustomNavTheme customNavTheme = new CustomNavTheme();
        customNavTheme.setIconsColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setNavigationBackground(Color.BLACK);
        customNavTheme.setSelectedItemBackgroundColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setSelectedItemIconColor(Color.WHITE);
        customNavTheme.setSelectedItemTextColor(Color.WHITE);
        customNavTheme.setTextColor(Color.WHITE);

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
        learnosetNavigationBar.addNavItem(LearnosetNavItem.BuiltInItems.FEEDBACK);
    }

    private void settingCustomGroup(){
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
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}