/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abrir_pdi_proyecto2;

/**
 *
 * @author luisf
 */
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Daniel Tovar
 */
public class Abrir_Pdi_Proyecto2 extends ImageProcessing {
    
    private BufferedImage openImage;
    private JFileChooser selectorImage;
    private imageFormat selectedExtensionImage;
        
    protected boolean openJFileChooser(){
        this.selectorImage=new JFileChooser();
        this.selectorImage.setDialogTitle("Imagen   ");
        selectImageFilter();
        int flag=this.selectorImage.showOpenDialog(null);
        if (flag==JFileChooser.APPROVE_OPTION){
            return true;
        }else{
           return false;
        }
    }   
    protected void selectImageFilter(){
        FileNameExtensionFilter imageFilter;
        imageFilter= new FileNameExtensionFilter("Todos los Archivos (*.*)","*.*");
        switch(this.selectedExtensionImage){
            case all:
                imageFilter = new FileNameExtensionFilter("Todos los Archivos (*.*)","*.*");
                break;
            case all_images:
                imageFilter = new FileNameExtensionFilter("Todos los Archivos","bmp","dib","gif",
                        "jpg","jpeg","jpe","jfif","png");
                break;
            case bmp:
                imageFilter = new FileNameExtensionFilter("Bitmap (*.bmp, *.dib)","bmp","dib");
                break;
            case gif:
                imageFilter = new FileNameExtensionFilter("GIF (*.gif)","gif");
                break;
            case jpg:
                imageFilter = new FileNameExtensionFilter("JPEG (*.jpg,*.jpeg,*.jpe,*.jfif)","jpg","jpeg","jpe","jfif");
                break;
            case png:
                imageFilter = new FileNameExtensionFilter("PNG (*.png)","png");
                break;   
        }
        this.selectorImage.setFileFilter(imageFilter);
    }
    protected String extractLocalImageName(String imagePath){
        int finalSlash=imagePath.lastIndexOf("\\");
        String nameReturn=imagePath.substring(finalSlash+1);
        return nameReturn;
    }
    protected String extractRemoteImageName(String imagePath){
        int finalSlash=imagePath.lastIndexOf("/");
        String nameReturn=imagePath.substring(finalSlash+1);
        return nameReturn;
    }
     
     
    public BufferedImage openFile(ImageProcessing.imageFormat defautlExtension){
        this.selectedExtensionImage=defautlExtension;
        this.openImage=null;
        if(this.openJFileChooser()==true){
            File imageFile=this.selectorImage.getSelectedFile();
            try {
                this.openImage = ImageIO.read(this.selectorImage.getSelectedFile());
                 if (this.openImage!=null){
                super.updateImage("Cargo Exitosamente - " + extractLocalImageName(imageFile.toString())
                        + " (" + imageFile.toString() + ")", this.openImage);
                super.newOpenImage("Cargo Exitosamente - " + extractLocalImageName(imageFile.toString()), this.openImage);
                }else{
                super.updateActivityLog("Error");
                }
            } catch (Exception e) {
                super.updateActivityLog("Error" + e.toString());
            }
        }        
        return this.openImage;
    }
    
     
}
