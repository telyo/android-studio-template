package temparms

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import temparms.res.layout.simpleLayout
import temparms.src.app_package.*
import temparms.src.armsManifest
import java.io.File

fun RecipeExecutor.armsRecipe(provider:ArmsPluginTemplateProviderImpl,data: ModuleTemplateData){
    if (provider.needActivity.value && !provider.isModule.value){
        mergeXml(armsManifest(provider), File(data.manifestDir,"AndroidManifest.xml" ))
    }
    if (provider.needActivity.value && provider.isModule.value){
        mergeXml(armsManifest(provider), File(data.manifestDir,"../debug/AndroidManifest.xml" ))
        mergeXml(armsManifest(provider), File(data.manifestDir,"AndroidManifest.xml" ))
    }
    if (provider.needActivity.value && provider.generateActivityLayout.value){
        save(simpleLayout(provider), File(data.resDir,"layout/${provider.activityLayoutName.value}.xml" ))
    }
    if (provider.needFragment.value && provider.generateFragmentLayout.value){
        save(simpleLayout(provider), File(data.resDir,"layout/${provider.fragmentLayoutName.value}.xml" ))
    }
    if (provider.needActivity.value){
        val activityFile = File(data.srcDir,"${provider.activityPackageName.value}/${provider.pageName}Activity.kt")
        save(armsActivity(provider),activityFile)
        open(activityFile)
    }
    if (provider.needFragment.value){
        val fragmentFile = File(data.srcDir,"${provider.fragmentPackageName.value}/${provider.pageName}Fragment.kt")
        save(armsFragment(provider),fragmentFile)
        open(fragmentFile)
    }

    if (provider.needContract.value){
        val contractFile = File(data.srcDir,"${provider.contractPackageName.value}/${provider.pageName}Contract.kt")
        save(armsContract(provider),contractFile)
    }
    if (provider.needPresenter.value){
        val presenterFile = File(data.srcDir,"${provider.presenterPackageName.value}/${provider.pageName}Presenter.kt")
        save(armsPresenter(provider),presenterFile)
    }
    if (provider.needModel.value){
        val modelFile = File(data.srcDir,"${provider.modelPackageName.value}/${provider.pageName}Model.kt")
        save(armsModel(provider),modelFile)
    }

    if (provider.needDagger.value){
        val componentFile = File(data.srcDir,"${provider.componentPackageName.value}/${provider.pageName}Component.kt")
        val moduleFile = File(data.srcDir,"${provider.componentPackageName.value}/${provider.pageName}Module.kt")
        save(armsContract(provider),componentFile)
        save(armsModule(provider),moduleFile)
    }
}