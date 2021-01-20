package Utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
 
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
 
    /*
     * NOTICE : JSearchBar. Data must be declared. This class will search inside.
     * Must be used with interface JSearchBarListener to catch events
     *
     * Created : 3rd May 2016
     * Last modification : 3rd May 2016
     */
 
public class JSearchBar extends JComboBox<String>
{
    private static final long serialVersionUID = -583042851446480423L;
     
    private Collection<String> data_list = new ArrayList<String>();
    private DefaultComboBoxModel<String> suggestions = new DefaultComboBoxModel<String>();
 
    private JTextField search_field;
     
    //Listeners
    private SearchFieldListener search_field_listener = new SearchFieldListener();
    private CheckFieldListener check_field_listener = new CheckFieldListener();
    private ChangeValueListener change_value_listener = new ChangeValueListener();
     
/*----------------------------------------------------------------------------------------------------*/
     
    //CONSTRUCTORS
    public JSearchBar(){
        LoadJSearchBar();
    }
     
    public JSearchBar(String[] datas){
        LoadJSearchBar();
        addData(datas);
    }
     
    //ENGINE
    protected void LoadJSearchBar(){
        this.setModel(suggestions);
        search_field = (JTextField)this.getEditor().getEditorComponent();
        search_field.addKeyListener(search_field_listener);
        search_field.addCaretListener(check_field_listener);
        search_field.addCaretListener(change_value_listener);
        this.setEditable(true);
        this.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);//Keyboards will no affect editor content
    }
     
    private class SearchFieldListener extends KeyAdapter{//Manage keyboards inputs in editor
        public void keyPressed(KeyEvent arg0) {
            if(arg0.getKeyCode() != KeyEvent.VK_ENTER)//Remove this listener to not notify observers for each keyboards input
                search_field.removeCaretListener(change_value_listener);
             
            switch(arg0.getKeyCode()){ //These keys must be ignored
                case KeyEvent.VK_UP:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_ENTER:
                    break;
                default:
                    SwingUtilities.invokeLater(new CreateSuggestions());
                }
             
            if(arg0.getKeyCode() != KeyEvent.VK_ENTER)//Restore listener
                SwingUtilities.invokeLater(new addChangeValueListener());
        }
    }
     
    private class ChangeValueListener implements CaretListener{//This Observer is remove frequently to notify observers only of necessary
        public void caretUpdate(CaretEvent arg0){
            NotifyObservers();
        }
    }
     
    private class CheckFieldListener implements CaretListener{//Removes html codes to display a correct string
        public void caretUpdate(CaretEvent arg0){
            SwingUtilities.invokeLater(new CheckSearchField());
        }
    }
     
    private class CreateSuggestions extends Thread{
        public void run(){
            String text_typed = search_field.getText();
            hidePopup();
             
            if(suggestions.getSize() != 0)
                suggestions.removeAllElements();//The suggestion model must be empty before fill it.
             
            if(text_typed.length() != 0)
                for(String data : data_list)//Iterate all data
                    if(Formate(data).indexOf(Formate(text_typed)) != -1)//If data correspond to suggestion
                    {   int index = Formate(data).indexOf(Formate(text_typed));
                        suggestions.addElement(
                             "<html>"+data.substring(0,index)
                             +"<font color=blue>"+data.substring(index,index + text_typed.length())+"</font>"
                             +data.substring(index +text_typed.length(),data.length())+"</html>");
                    }
             
            showPopup();
            search_field.setText(text_typed);//This text will be check by CheckFieldListener
        }
    }
     
    private class CheckSearchField extends Thread{
        public void run(){//Removes html codes to display a correct string. Call by CheckFieldListener
            search_field.removeCaretListener(check_field_listener);//Avoid auto-check and infinite loop
            search_field.setText(search_field.getText().replaceAll("<[^>]*>", ""));//Delete html tags
            search_field.addCaretListener(check_field_listener);
        }
    }
     
    private class addChangeValueListener extends Thread{//Restore listener which notifies observers after treatments
        public void run(){
            search_field.addCaretListener(change_value_listener);
        }
    }
     
    public void showPopup(){//Redefine method to not display empty popup
        super.showPopup();
        if(suggestions.getSize() == 0)
            hidePopup();
    }
     
    protected String Formate(String text_to_formate){//Formate strings. Useful for compared.
        String formate_text = text_to_formate.toLowerCase()
                                             .replaceAll(" ","_")
                                             .replaceAll("à","a")
                                             .replaceAll("â","a")
                                             .replaceAll("ç","c")
                                             .replaceAll("é","e")
                                             .replaceAll("ê","e")
                                             .replaceAll("è","e")
                                             .replaceAll("î","i")
                                             .replaceAll("ô","o")
                                             .replaceAll("ù","u")
                                             .replaceAll("û","u");
        return formate_text;
    }
     
    //PUBLICS METHODES
        //(INPUTS)
    public void addData(String data){
        data_list.add(data);
    }
    public void addData(String[] datas){
        for(String data : datas)
            data_list.add(data);
    }
        //(OUTPUTS)
    public String getSelectedData(){
        return search_field.getText();
    }
        //OBSERVER
    ArrayList<JSearchBarListener> list_observer = new ArrayList<JSearchBarListener>();
 
    public void addJSearchBarListener(JSearchBarListener o){
        list_observer.add(o);
    }
    public void removeJSearchBarListener(JSearchBarListener o){
        list_observer.remove(o);
    }
    protected void NotifyObservers(){
        for(String s : data_list)
            if(Formate(s).equals(Formate(search_field.getText())))
                for(JSearchBarListener obs : list_observer)
                    obs.dataSelected();
    }
}
