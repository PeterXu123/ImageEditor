Before introducing you to my main function, I would like to introduce you to my three directories 
and their sub-directories.  There are three directories that are res, src and test directory. In 
res directory, there are 6 sub-directories. You can probably know what each sub-directory contains 
just based on their names. "users" directory will be used when you run the main function that is in
ImageRun file. There are two images in users that are original images for all images in 
OnePunchImage and Manhattan images. When you run the main function, and choose to modify existing 
images, you will have to pick/load a image in "users" directory. If you want to add your own image, 
you can add the image to "users" directory. All created images will be stored in users directory.

Now, let's talk about how to run the main function. You can run it in ImageRun file that is located
in src directory. The instruction I give is very specific. You know what to give even without 
reading this file. After you click run, you will need to enter 1 or 2. 1 represents the option of 
generating images. 2 represents the option of modifying existing images in "users" directory. 
If you pick generating images,  you will see this line "Enter 1 for generating checkerboard. Enter
 2 for generating flags. Enter 3 for generating rainbow." You have three options here (checkerboard
 , flags, and rainbows. If you enter 1, you will be asked to give the size of the checkerboard. The
 size you give should be greater than 10. After giving the size, you will be asked to give the 
 number of squares per side. Your input should be greater than 2. If you give correct input for 
 the size of checkerboard, and the number of squares per side, you will see the new image created in 
 users directory. If you give wrong input, you will be asked to enter the input again.
  
  If you choose to generate flags, you will be asked to enter a number that represents
 which flag you want to generate. The instruction line you will see is "Enter 1 for France flag. 
 Enter 2 for Greece flag. Enter 3 for Norway flag. Enter 4 for Switzerland flag". After choosing a
 flag, you will be asked to give a height for the flag that should be greater than 30. There is a
 very important thing you should know. Each flag has its proportion. If the height you give cannot
 produce a proper width, you will be asked to give a correct one. For example, the proportion of
 Norway flag is 11/8 = width / height. If you give 32 as its height, its width will be 44. However, 
 if you give 33, the width will be 45.375 that is not an integer. For this case, you will be asked 
 to give reasonable height. You will see the proportion of each flag once you give improper height.
 
 If you choose to generate rainbow, you will be asked which rainbow you want to create. The opiton
 you have includes vertical rainbow and horizontal rainbow. You enter v for vertical rainbow, and enter
 h for horizontal rainbow. Once you choose one, you will be asked to give width and height of the rainbow
 that both should be greater than 20. you will give width and height at the same time ("Enter a integer,
 press enter key, and enter another integer, press enter key again"). You will be asked to enter
 inputs if one of them is less than 20.
 
Now, if you enter 2 for modifying existing images, you will be asked if you want to load image 
from your local or from "users" directory. If you choose enter 1 that represents loading image from "users",
you see the line "choose files listed below hohggkphlv.jpeg or manhattan-small.png", 
you need to enter the file whole name like manhattan-small.png.
If you have added other images into users directory, you can enter the file's whole name in order to operation 
on the images. After entering the file name, you will see "blur, sharpen, grey, and sepiaTone". You 
will need to enter the name of operation to operate on the image you picked before. For exmaple, you will
enter "blur" if you want to blur the image you choose before. If you give wrong input, you will be asked
to enter again. If you enter 1 that represents loading image from local, you will be asked to provide 
absolute path of the image. After that, you will be asked to choose operation, and the rest is same. 
Image will be generated in "users" directory.
 
If you only want to see how those images in checkerboards, flags, Manhattan, OnePunchImage and rainbows,
you can delete those images on these sub-directories, but do not delete the sub-directories. Then,
you run the ImageModel2DTest. You would see images created under these sub-directory.

how to use images in "users" directory. All you need to give is the whole name of the file including
the suffix like JPG. Example user input: hohggkphlv.jpeg or manhattan-small.png.

how to use  images on your local (your laptop): you need to give the absolute path of the image
file. 


Citation of hohggkphlv.jpeg is from website https://www.0311xue.com/tuwen/5068577.html.

Citation of manhattan-small.png is from https://northeastern.instructure.com/courses/664/assignments/7596.




Updates:
First of all, I changed a lot of things. Let's talk about the changes I made in the model interface.
I changed all method's return type to void. The reason for it is because It's flexible for other people who
want to use my interface. They don't have to return a three dimensional array for these image generation methods (flags, checkerboard, rainbow) and image
modification methods (blur, sharpen, greyscale, dithering, edgeDetection). Besides, I don't have to create a copy for the image data.
I used to create a copy image data that was returned in these methods. The reason for it is that I didn't want users to modify the image
data in the model directly, so I returned image data copy. Now, I don't need to return any image data from the model, so the users won't have
chance to modify the data of the model without calling methods in the model. They have to call those methods in order to modify the image data.   
I also added the save and store methods in the model, which makes more sense according to OOD principle.
Another change I made is to delete all unnecessary features like undo, redo and their corresponding stacks in the model.
Besides, I deleted AbstractImageGeneration abstract class. I put its method directly into model class and let the model implement the model interface directly.
The reason for it is because abstract class made many things complicated, and made it difficult to encapsulate fields and methods (I need a lot of helper methods).
Last but not least, I combined two rainbow methods into one method. The new method's parameters include a boolean that determines if the
rainbow is vertical or horizontal. Other parameters are same as old ones'. The reason for it is to reduce duplicate code. 

Instructions on how to use the JAR file.    
In order to successfully run jar file with your input text file, your jdk should be match to 13 or higher.
The commands includes: save, load, dither, blur, sharpen, sepia, greyscale, highlightEdge, checkerboard, rainbow
and flag.

Format of save: 
save yourFilePath

Format of load:
load yourFilePath


Format of dither, blur, sharpen, sepia, greyscale, highlightEdge, contrastEnhancement, mosaics:
dither
blur
sharpen
sepia
greyscale
highlightEdge
contrastEnhancement
mosaics [seed Number]


Format of checkerboard:
checkerboard {number of square per side} {size of the image}

Format of rainbow:
rainbow vertical height width
rainbow horizontal height width

Format of flag (Country includes: Norway, Switzerland, France and  Greece):
flag {Country name} height
eg: flag Norway 120


If the input is incorrect, the image of the input might not be created. Especially, you should 
be careful for giving height for the flag. If it doesn't fit to the flag proportion (the corresponding
width is not an integer based on the height you give), it won't create the flag.

I still keep the images I had submitted for last homework. You have 2 images in res/users folder.
As for these two images' corresponding highlights edges, and dithered images, I put them into res/Manhattan,
and res/OnePunchImage folders.

Sample input files content:
load D:\cs5010\projects\hw4\res\users\manhattan-small.png
dither
save D:\cs5010\projects\hw4\res\users\manhattan-dither.png
load D:\cs5010\projects\hw4\res\users\manhattan-small.png
blur
save D:\cs5010\projects\hw4\res\users\manhattan-blur.png
sepia
save D:\cs5010\projects\hw4\res\users\manhattan-blur-sepia.png
checkerboard 4 100
save D:\cs5010\projects\hw4\res\users\checkerboard.png
load D:\cs5010\projects\hw4\res\users\hohggkphlv.jpeg
dither
save D:\cs5010\projects\hw4\res\users\hohggkphlv-dither.jpeg
load D:\cs5010\projects\hw4\res\users\manhattan-small.png
highlightEdge
dither
save D:\cs5010\projects\hw4\res\users\manhattan-edgedither.png
flag France 30
save D:\cs5010\projects\hw4\res\users\France-Flag.png
rainbow vertical 500 500
save D:\cs5010\projects\hw4\res\users\vertical-rainbow.png
flag Norway 120
save D:\cs5010\projects\hw4\res\users\norway.png
rainbow horizontal 500 500
save D:\cs5010\projects\hw4\res\users\vertical-rainbow.png
flag Greece 30
save D:\cs5010\projects\hw4\res\users\Greece-Flag.png
flag Switzerland 32
save D:\cs5010\projects\hw4\res\users\Switzerland-Flag.png
load D:\cs5010\projects\hw4\res\users\manhattan-small.png
sharpen
save D:\cs5010\projects\hw4\res\users\manhattan-sharpen.png
mosaics 5
save D:\cs5010\projects\hw4\res\users\manhattan-sharpentobedeleted.png
contrastEnhancement
save D:\cs5010\projects\hw4\res\users\manhattan-sharpentobedeleted1.png

(Note: do not leave extra line in your file)

updates for final version:
I have updated the commands that will be used in the script. You can find them in the sample above or in command introduction part.
The following part is telling you how to use the view application. By the way, the application has all features.


How do you use the view application?

For menu options:
1. You can load and save image from Load/Save menu. When you save a file, give complete file name
   with suffix. like pp.png
   
2. You can generate checkerboard, country flags, horizontal rainbow, and vertical rainbow through the 
   The second menu Image_Generation. Once you click any menu item that I mentioned above, small windows will
   pop up and ask you for the inputs that each option needs. 
3. The last menu is Enhancement.  You can enhance the current view by adding a border, a sticker or 
   texts.  'Add Border' requires the size of the border and a color for the border. 'Add text' will ask you for
   the text across the top of the current image and the text across the bottom of the current image. You should be careful of
   'Add sticker'. Before you click 'Add sticker', you should use your mouse click a position in the view that you want the stick
   be. After you click the 'Add sticker', you will be asked to pick a image file and then you will also be asked for a background color
   for the sticker. The background color will be transparent in the view.

For buttons on the right side of the view application:
1. click blur -> blur the current image 
2. click sharpen -> sharpen the current image
3. click sepia -> sepia the current image
4. click highlightEdge -> highlight the edge of the current image
5. click greyScale -> greyScale the current iamge
6. click mosaics -> pop up a window that ask you for the number of seed. you will give a positive integer.
7. click dithering -> dithering the current image
8. click equalization -> uses image intensity to increase the contrast of an image.
9. below.
Pay attention to remove red eyes button.
Now you need to be careful for remove red eyes button. Before you click these button, you should  
press-and-drag-and-release) mouse to select the part of the image that you want to apply the red-eye removal averaging.
Your selected area will not be highlighted in the view so you need to be careful and remember the position you press the mouse and the position
you release the mouse. The selected area will be created based on these two points. Finally, you could click the button, and you will find that the red eyes disappear.

10. click select script button -> ask you to select the script file and it will generate the images based on the script file.

11. click run script from text below button -> run the script from the text area of the application. 
 


Citations:   
Citation of hohggkphlv.jpeg  website https://www.0311xue.com/tuwen/5068577.html.
Citation of manhattan-small.png https://northeastern.instructure.com/courses/664/assignments/7596.
Citation of red eyes girl and some image in HistogramEqulized  https://northeastern.instructure.com/courses/664/assignments/7598
Citation of red eyes boy https://en.wikipedia.org/wiki/Red-eye_effect
other https://en.wikipedia.org/wiki/Histogram_equalization, 



Lab images are included Manhattan and OnePunchImage folder.

 
 
 

