



var firefox = Application('Firefox'); // app to run script in

firefox.doJavaScript('window.print();', {
  in: firefox.windows[0].currentTab
})


