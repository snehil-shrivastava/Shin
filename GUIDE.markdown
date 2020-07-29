## Guide for developing this app.

### 1.  General rule and naming conventions :-
1.1 *Kotlin Guideline*

  1. Declare single top level ie use separate file for declaring each class.
  2. Use object for declaring utility classes. ie

	           object Utils{    
	                @JvmStatic fun doSomething(){  
	                }    
	           }  
   3. Don't use anonymous class and local class use lambda instead.
   4. Apply PascalCase
   5. Use separate file for `PackageNameExtentions.kt`and `ClassNameConstants.kt` in each package wherever needed.
   6. Prefer static nested class as **static nested classes don't store the reference of container class**
   7. Use symbols directly instead of Unicode ie prefer `∞` over `\u221e`
   8.  A `.kt`  file comprises the following, in order :
		-   Copyright and/or license header (optional)
		-   File-level annotations
		-   Package statement
		-   Import statements
		-   Top-level declarations
   9. Use this style of comment

			/*  
			 * Copyright 2017 Google, Inc.  
			 * ...  
			 */  

		Do not use a  [KDoc-style](https://kotlinlang.org/docs/reference/kotlin-doc.html)  or single-line-style comment.

			/**  
			 * Copyright 2017 Google, Inc.  
			 *  
			 * ...  
			 */  
			-------------- OR ----------------
			// Copyright 2017 Google, Inc.  
			//  
			// ...
   10. Annotations with the "file"  [use-site target](https://kotlinlang.org/docs/reference/annotations.html#annotation-use-site-targets)  are placed between any header comment and the package declaration.
      <br/>**More info** - [# Advanced Kotlin - Part 2 - Use-Site Targets]([https://americanexpress.io/advanced-kotlin-use-site-targets/#:~:text=Simply%20put%2C%20annotation%20use%2Dsite,Java%20code%20generated%20by%20kapt%20.](https://americanexpress.io/advanced-kotlin-use-site-targets/#:~:text=Simply%20put%2C%20annotation%20use%2Dsite,Java%20code%20generated%20by%20kapt%20.))
		and  [Delegates]([https://americanexpress.io/advanced-kotlin-delegates/](https://americanexpress.io/advanced-kotlin-delegates/)) (if you don't know already please read this)


read more on [https://developer.android.com/kotlin/style-guide](https://developer.android.com/kotlin/style-guide)

1.2 *Resources*
| Asset Type   | Prefix             |
|--------------| -------------------|
| Action bar   | `ab_`              |
| Button       | `btn_`	            |
| Dialog       | `dialog_`          |
| Divider      | `divider_`         |
| Icon         | `ic_`	            |
| Menu         | `menu_	`           |
| Notification | `notification_`	|
| Tabs         | `tab_`             |

@Also read - [https://jeroenmols.com/blog/2016/03/07/resourcenaming/](https://jeroenmols.com/blog/2016/03/07/resourcenaming/)

1.3 *View Model Injection With Safe Handle*

    class SomeViewModel @AssistedInject constructor(
        private val application: Application,
        @Assisted private val savedStateHandle: SavedStateHandle
    ){
        // must be inside of the ViewModel class!
        @AssistedInject.Factory
        interface Factory : AssistedSavedStateViewModelFactory<SomeViewModel> {
            override fun create(savedStateHandle: SavedStateHandle): SomeViewModel  // may be ommited prior kotlin 1.3.60 or after PR #121 in AssistedInject lib
        }
    }

and add this as module

@AssistedModule
@Module(includes=[AssistedInject_BuilderModule::class])
abstract class BuilderModule {
    @Binds
    @IntoMap
    @ViewModelKey(SomeViewModel::class)
    abstract fun bindVMFactory(f: SomeViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}

---
### 2. Using Dokka for generation of documents.
Apply the plugin to the `build.gradle` of the module(s) for which you would like to generate documentation

	 apply plugin: 'org.jetbrains.dokka' dokka { 
		 outputFormat = 'html' // use 'javadoc' to get standard java docs
		 outputDirectory = "$buildDir/javadoc"
		 configuration {
			  includeNonPublic = false
			  skipEmptyPackages = true
			  skipDeprecated = true 
			  reportUndocumented = true 
			  jdkVersion = 8
		}
	}
