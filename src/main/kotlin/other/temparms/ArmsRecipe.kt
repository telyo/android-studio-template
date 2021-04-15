package other.temparms

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import org.jetbrains.kotlin.idea.versions.LOG
import other.temparms.res.layout.simpleLayout
import other.temparms.src.app_package.*
import other.temparms.src.armsManifest
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
        val activityFile = File(data.srcDir,"${fFmSlashedPackageName(provider.activityPackageName.value)}/${provider.pageName.value}Activity.kt")
        save(armsActivity(provider),activityFile)
        open(activityFile)
    }
    if (provider.needFragment.value){
        val fragmentFile = File(data.srcDir,"${fFmSlashedPackageName(provider.fragmentPackageName.value)}/${provider.pageName.value}Fragment.kt")
        save(armsFragment(provider),fragmentFile)
        open(fragmentFile)
    }

    if (provider.needContract.value){
        val contractFile = File(data.srcDir,"${fFmSlashedPackageName(provider.contractPackageName.value)}/${provider.pageName.value}Contract.kt")
        save(armsContract(provider),contractFile)
    }
    if (provider.needPresenter.value){
        val presenterFile = File(data.srcDir,"${fFmSlashedPackageName(provider.presenterPackageName.value)}/${provider.pageName.value}Presenter.kt")
        save(armsPresenter(provider),presenterFile)
    }
    if (provider.needModel.value){
        val modelFile = File(data.srcDir,"${fFmSlashedPackageName(provider.modelPackageName.value)}/${provider.pageName.value}Model.kt")
        save(armsModel(provider),modelFile)
    }

    if (provider.needDagger.value){
        val componentFile = File(data.srcDir,"${fFmSlashedPackageName(provider.componentPackageName.value)}/${provider.pageName.value}Component.kt")
        val moduleFile = File(data.srcDir,"${fFmSlashedPackageName(provider.componentPackageName.value)}/${provider.pageName.value}Module.kt")
        save(armsContract(provider),componentFile)
        save(armsModule(provider),moduleFile)
    }
}
fun fFmSlashedPackageName(oVar:String): String {

    return oVar.replace('.', '/')
}