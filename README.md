# FloatOptionButton

[![Developer](https://img.shields.io/badge/developer-sina%20dalvand-orange)](https://github.com/sinadalvand)
[![Lisense](https://img.shields.io/badge/License-Apache%202-lightgrey.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://img.shields.io/maven-central/v/ir.sinadalvand.libs/floatoptionbutton?label=version%3A) ](https://bintray.com/sinadalvand/maven/FloatOptionButton/1.0.2/link)


🎈 Float Option Button is a Float Action Button (fab)  with two option button drawer that can expand and collapse with many custom attrs



<img src="https://github.com/sinadalvand/FloatOptionButton/blob/master/art/image.gif" width="250"/>


## screenshot
<img src="https://github.com/sinadalvand/FloatOptionButton/blob/master/art/preview.gif" width="250"/>


## Gradle & Maven
```
 implementation 'ir.sinadalvand.libs:floatoptionbutton:1.0.2'
```
```
<dependency>
	<groupId>ir.sinadalvand.libs</groupId>
	<artifactId>floatoptionbutton</artifactId>
	<version>1.0.2</version>
	<type>pom</type>
</dependency>
```


## How use this Fob :
```	
<ir.sinadalvand.floatoptionbutton.FloatOptionButton
	android:id="@+id/floatOptionButton"
	app:fob_animDuration="300"                  ==> animation duration to open or close
	app:fob_autoCollapseInClick="false"         ==> auto collapse after click on any button of this view
	app:fob_backgroundColor="#f66767"           ==> color background of expanded rabon
	app:fob_buttonColor="@android:color/white"  ==> main button color (center float action button)
	app:fob_scale="3.5"                         ==> scale size of option width against collapse size
	app:fob_open="false"                        ==> if true view will be expanded as default 
	app:fob_leftIcon="@drawable/ic_image"       ==> left button icon as Drawable
	app:fob_rightIcon="@drawable/ic_airplay"    ==> right button icon as Drawable
	app:fob_mainIcon="@drawable/ic_lock"        ==> main button (center button) icon as Drawable
	app:fob_scaleWidth="100dp"                  ==> width fix size for expanded (it will overrite to fob_scale)
	/>
```


### Buttons click Listener: :
```
   floatOptionButton.setFloatOptionListener(object :FloatOptionButtonListener{
		override fun mainButtonClicked(view: View) {
			// main button clicked! (center button)
		}

		override fun leftButtonClicked(view: View) {
			// left button clicked! (center button)
		}

		override fun rightButtonClicked(view: View) {
		   // right button clicked! (center button)
		}

	})
```



### For toggle between Collapse and Expanded :
```
   floatOptionButton.toggle()
```


### For Collapse:
```
   floatOptionButton.collapse()
```

### For Expand:
```
   floatOptionButton.expand()
```


and also can set all thr xml attrs by code:
```
floatOptionButton.setMainButtonColor(color: Int) 
floatOptionButton.setSidesButtonColor(color: Int)
floatOptionButton.setFobBackgroundColor(color: Int)
floatOptionButton.setMainButtonIcon(res: Int)
floatOptionButton.setLeftButtonIcon(res: Int)
floatOptionButton.setRightButtonIcon(res: Int)
floatOptionButton.isOpen(boolean: Boolean)
floatOptionButton.setScale(scale: Float)
floatOptionButton.setAnimationDuration(duration: Int)
floatOptionButton.setMaxWidth(width: Int)
```


and if you want to apply more customization , you can get buttons by : 
```
floatOptionButton.getMainButton()   ==> FloatingActionButton
floatOptionButton.getRightButton()  ==> ImageView
floatOptionButton.getLeftButton()   ==> ImageView
```

