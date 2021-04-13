package temparms.src.app_package

import temparms.ArmsPluginTemplateProviderImpl

fun armsActivity(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}

import android.content.Intent
import android.os.Bundle

import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.dresses.library.base.BaseMvpActivity

import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter

import ${provider.appPackageName.value}.R

class ${provider.pageName.value}Activity : BaseMvpActivity<${provider.pageName.value}Presenter>() , ${provider.pageName.value}Contract.View {

    override fun setupActivityComponent(appComponent:AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1,provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState:Bundle?):Int {
              return R.layout.${provider.activityLayoutName.value} //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    override fun initTitle() {
             //如果不需要header不作任何操作
             // getHeaderBuild().setTitle("Start activity").build()
    }
    override fun initDataContinue(savedInstanceState: Bundle?) {

    }


}
    
    
"""