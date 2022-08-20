# AnimatedIconButton

This is a new library I made to animate an ImageButton on click.

## You can:
- Set multiple resources as it will toggle progressively through them
- Toggle to a specific drawable
- Add a listener to listen to the drawable change callback

## Here are some multiple use cases :

https://user-images.githubusercontent.com/32796182/185722085-0294167f-18a3-41dc-b8e2-9c39100f734c.mp4

## Add dependency:

- Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://www.jitpack.io' }
	}
}
```

- Add the dependency
```
dependencies {
	implementation 'com.github.ndriqa:AnimatedIconButton:1.0'
}
```

## How to use it:
- Set up the button drawables you want to use: 
```
binding.bluetoothButton.setResources(
  R.drawable.ic_bluetooth_disabled,
  R.drawable.ic_bluetooth_enabled,
  R.drawable.ic_bluetooth_connected,
)
```

- Add on click listener to toggle through drawables:
```
binding.bluetoothButton.setOnClickListener {
  binding.bluetoothButton.toggle()
}
```

- You can also set an OnDrawableChangeListener
```
binding.bluetoothButton.setOnDrawableChangeListener(object : OnDrawableChangeListener {
  override fun onDrawableChange(drawableResId: Int) {
    binding.bluetoothStateTextView.text = when(drawableResId) {
      R.drawable.ic_bluetooth_disabled -> "off"
      R.drawable.ic_bluetooth_enabled -> "on"
      R.drawable.ic_bluetooth_connected -> "connected"
      else -> "unknown"
    }
  }
})
```

- You can optinally toggle straight to the drawable you want by passing an argument to the **toggle(drawableID)**

## What can this library be used for?

- Like/Unlike button
- State holding buttons (Video quality, bluetooth connections, video aspect ratio etc etc)
- Catalogue selection (see last example in the demo video above...)
- etc

## Donate

[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/U7U2EEM2E)
