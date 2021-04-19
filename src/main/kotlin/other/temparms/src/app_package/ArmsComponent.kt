package other.temparms.src.app_package

import other.temparms.ArmsPluginTemplateProviderImpl

fun armsComponent(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.componentPackageName.value}

import dagger.Component
import com.jess.arms.di.component.AppComponent

import ${provider.moudlePackageName.value}.${provider.pageName.value}Module

${if (provider.needActivity.value && provider.needFragment.value)
    """
import com.jess.arms.di.scope.ActivityScope
import ${provider.activityPackageName.value}.${provider.pageName.value}Activity
import ${provider.fragmentPackageName.value}.${provider.pageName.value}Fragment
"""
else if (provider.needActivity.value)
    """
import com.jess.arms.di.scope.ActivityScope
import ${provider.activityPackageName.value}.${provider.pageName.value}Activity
"""
else if (provider.needFragment.value)
    """
import com.jess.arms.di.scope.FragmentScope
import ${provider.fragmentPackageName.value}.${provider.pageName.value}Fragment
"""
else ""
}

${if (provider.needActivity.value && provider.needFragment.value)
    "@ActivityScope"
else if (provider.needActivity.value)
    "@ActivityScope"
else if (provider.needFragment.value)
    "@FragmentScope"
else ""
}
@Component(modules = arrayOf(${provider.pageName.value}Module::class),dependencies = arrayOf(AppComponent::class))
interface ${provider.pageName.value}Component {
  ${if (provider.needActivity.value && provider.needFragment.value) {
    """
    fun inject(activity:${provider.pageName.value}Activity)
    fun inject(fragment:${provider.pageName.value}Fragment)
    """
} else if (provider.needActivity.value || provider.needFragment.value)
    """
    fun inject( ${if (provider.needFragment.value) "fragment:${provider.pageName.value}Fragment)" else "activity:${provider.pageName.value}Activity)\n"}"""
else ""
}
}
    
"""