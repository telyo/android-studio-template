package other.temparms.src.app_package

import other.temparms.ArmsPluginTemplateProviderImpl

fun armsPresenter(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.presenterPackageName.value}

import android.app.Application

import com.jess.arms.integration.AppManager

${if (provider.needActivity.value && provider.needFragment.value)
    "import com.jess.arms.di.scope.ActivityScope"
else if (provider.needActivity.value)
    "import com.jess.arms.di.scope.ActivityScope"
else if (provider.needFragment.value)
    "import com.jess.arms.di.scope.FragmentScope"
else ""
}

import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import ${provider.contractPackageName.value}.${provider.pageName.value}Contract


${if (provider.needActivity.value && provider.needFragment.value)
    "@ActivityScope"
else if (provider.needActivity.value)
    "@ActivityScope"
else if (provider.needFragment.value)
    "@FragmentScope"
else ""
}
class ${provider.pageName.value}Presenter
@Inject
constructor(model: ${provider.pageName.value}Contract.Model, rootView: ${provider.pageName.value}Contract.View) :
BasePresenter<${provider.pageName.value}Contract.Model, ${provider.pageName.value}Contract.View>(model,rootView) {
    @Inject
    lateinit var mErrorHandler:RxErrorHandler
    @Inject
    lateinit var mApplication:Application
    @Inject
    lateinit var mImageLoader:ImageLoader
    @Inject
    lateinit var mAppManager:AppManager


    override fun onDestroy() {
          super.onDestroy();
    }
}   
"""