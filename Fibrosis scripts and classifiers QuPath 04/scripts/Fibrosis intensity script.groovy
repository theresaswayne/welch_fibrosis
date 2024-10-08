//set image type
setImageType('BRIGHTFIELD_OTHER');
//select annotations
selectAnnotations();
//merge annotations
mergeSelectedAnnotations()

//average intensity quantification for merged annotations
runPlugin('qupath.lib.algorithms.IntensityFeaturesPlugin', '{"pixelSizeMicrons":1.0,"region":"ROI","tileSizeMicrons":25.0,"colorOD":false,"colorStain1":false,"colorStain2":false,"colorStain3":true,"colorRed":false,"colorGreen":false,"colorBlue":false,"colorHue":false,"colorSaturation":false,"colorBrightness":false,"doMean":true,"doStdDev":false,"doMinMax":false,"doMedian":false,"doHaralick":false,"haralickDistance":1,"haralickBins":32}')

//get key and values for annotations
def annotations = getAnnotationObjects()

//get individual key for intensity quantification
for (annotation in getAnnotationObjects()){
x = measurement(annotation, "ROI: 1.00 µm per pixel: Residual: Mean")
}

//optimization for intensity quantification
//for fibrosis with acetic acid counterstain:
//z = (x*10)
//for fibrosis with fast green counterstain:
z = -(x*0.1)

//get pre-saved or pre-generated Fibrosis file and write to it
//**IMPORTANT** You must include your own path directory below for the script to function properly
//def file = new File('YOUR PATHNAME GOES HERE/Fibrosis.json')
//EXAMPLE: def file = new File('/Users/tcs6/Desktop/Carrie Welch/LiverQuant practice/classifiers/pixel_classifiers/Fibrosis.json')
def file = new File('/Users/theresaswayne/Desktop/Carrie Welch/2023-12 Fibrosis Heart/classifiers/pixel_classifiers/Fibrosis.json')


def newConfig = file.text.replaceAll(' 0.0', z.toString())

//Write new file to existing Config
//**IMPORTANT** You must include your own path directory below for the script to function properly
//new File('YOUR PATHNAME GOES HERE/FibrosisQuant.json').write(newConfig, "utf-8")
//EXAMPLE: new File('/Users/tcs6/Desktop/Carrie Welch/LiverQuant practice/classifiers/pixel_classifiers/FibrosisQuant.json').write(newConfig, "utf-8")
new File('/Users/theresaswayne/Desktop/Carrie Welch/2023-12 Fibrosis Heart/classifiers/pixel_classifiers/FibrosisQuant.json').write(newConfig, "utf-8")
