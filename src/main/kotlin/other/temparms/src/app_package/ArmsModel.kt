package other.temparms.src.app_package

import other.temparms.ArmsPluginTemplateProviderImpl

fun armsModel(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.modelPackageName.value}

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

${if (provider.needActivity.value && provider.needFragment.value)
    "import com.jess.arms.di.scope.ActivityScope"
else if (provider.needActivity.value)
    "import com.jess.arms.di.scope.ActivityScope"
else if (provider.needFragment.value)
    "import com.jess.arms.di.scope.FragmentScope"
else ""
}
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
class ${provider.pageName.value}Model
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), ${provider.pageName.value}Contract.Model{
    @Inject
    lateinit var mGson:Gson;
    @Inject
    lateinit var mApplication:Application;

    override fun onDestroy() {
          super.onDestroy();
    }
}   
"""