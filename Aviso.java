import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

@SuppressWarnings("unused")
public class Aviso {

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public void main(String[] args, int x) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(450, 200);
		shell.setText("Aviso");
		
		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnOk.setBounds(182, 111, 75, 25);
		btnOk.setText("OK");
		
		Label aviso = new Label(shell, SWT.NONE);
		aviso.setBounds(35, 45, 375, 60);
		//aviso.setText("New Label");
		
		if (x == 1) {
			aviso.setText("Posição inválida, selecione outra!");
		} else if (x == 2) {
			aviso.setText("Vencedor: VOCÊ!");
		} else if (x == 3) {
			aviso.setText("Vencedor: PC!");
		} else {
			aviso.setText("Tabuleiro cheio! Fim de jogo\nEmpate!");
		}
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}    
}
