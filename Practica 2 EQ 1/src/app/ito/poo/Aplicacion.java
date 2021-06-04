package app.ito.poo;
import java.time.LocalDate;
import Clases.ito.poo.CuentaBancaria;
import Clases.ito.poo.CuentasdeBanco;
import javax.swing.JOptionPane;

public class Aplicacion {

static CuentasdeBanco c;
	
	static void menu() {
		inicializa();
		boolean ciclo=true;
		int respuesta=0;
		while(ciclo) {
		String opciones="Indique que desea realizar:\n 1)Agregar una nueva cuenta\n 2)Mostrar las cuentas existentes\n 3)Hacer un depósito a una cuenta existente\n "
				+ "4)Hacer un retiro a una cuenta\n 5)Eliminar una cuenta\n 6)Consulta\n 7)Salir";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:nuevaCuenta();break;
		case 2:cuentasExistentes();break;
		case 3:deposito();break;
		case 4:retiro();break;
		case 5:eliminarCuenta();break;
		case 6:consulta();break;
		case 7:ciclo=false;break;
		default:JOptionPane.showMessageDialog(null,"Ingrese aqui la opción deseada");
		  }
		}
	}
	
	static CuentaBancaria datosNCuenta() {
		CuentaBancaria n=new CuentaBancaria();
		long l;String fecha,nombre;float saldo;
		l=Long.parseLong(JOptionPane.showInputDialog("Numero de cuenta deseado:"));
		nombre=JOptionPane.showInputDialog("Nombre del titular:");
		saldo=Float.parseFloat(JOptionPane.showInputDialog("Saldo inicial:"));
		fecha=JOptionPane.showInputDialog("Fecha de apertura(año-mes-día):");
		n.setNumCuenta(l);
		n.setNomCliente(nombre);
		n.setSaldo(saldo);
		n.setFechaApertura(LocalDate.parse(fecha));
		return n;
	}
	
	static void inicializa() {
		c=new CuentasdeBanco();
	}
	
	static void nuevaCuenta() {
		CuentaBancaria nueva;
		nueva=datosNCuenta();
		if(c.addItem(nueva))
			JOptionPane.showMessageDialog(null,"Se agregó la cuenta creada :)");
		else
			JOptionPane.showMessageDialog(null,"Esta cuenta ya existe :(");
	}
	
	static void cuentasExistentes() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"No hay cuentas");
		else {
		String cuentas="";
		for(int i=0;i<c.getSize();i++)
			cuentas=cuentas+"\n"+(c.getItem(i));
		JOptionPane.showMessageDialog(null,cuentas);
		}
	}
	static void deposito() {
		int pos=0;
		float cantidad=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"No hay cuentas");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Elija a quien hará el deposito\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    cantidad=Float.parseFloat(JOptionPane.showInputDialog("Indique la cantidad a depositar"));
		    c.getItem(pos-1).setSaldo(c.getItem(pos-1).getSaldo()+cantidad);
		    c.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    JOptionPane.showMessageDialog(null,"Muchas gracias por regalarme dinero :)");
		    bandera=false;
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"Esa cuenta no existe, pero si quiere me lo puede dar a mi :)");
			}
		}
	}
	
	static void retiro() {
		int pos=0;
		float cantidad=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"No hay cuentas aún");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Cuenta a la que deseas retirar\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    cantidad=Float.parseFloat(JOptionPane.showInputDialog("Indique cantidad a retirar"));
		    if(!(c.getItem(pos-1).getSaldo()<cantidad)) {
		    c.getItem(pos-1).setSaldo(c.getItem(pos-1).getSaldo()-cantidad);
		    c.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    JOptionPane.showMessageDialog(null,"Cuidado al salir, no le vayan a robar");
		    bandera=false;
		    }
		    else {
		    	JOptionPane.showMessageDialog(null,"Eres demasiado pobre, no cuentas con esa cantidas :(");
		    }
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"No existe esa cuenta, si quieres pide un prestamo");
			}
		}
	}
	
	static void eliminarCuenta() {
		int pos=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Sin cuentas aún");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Indique que cuenta desea eliminar\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    	c.clear(c.getItem(pos-1));
		    	JOptionPane.showMessageDialog(null,"Esperemos que regrese con nosotros :(");
		    	bandera=false;
		    }	
		    else
		    	JOptionPane.showMessageDialog(null,"Que quiere dar de baja si esa cuenta no existe");
		  }
		}
	}
	
	static void consulta() {
		int respuesta=0;
		boolean ciclo=true;
		while(ciclo) {
		String opciones="Indique que desea hacer y lo atenderemos :):\n 1)Total de dinero en cuentas\n 2)Promedio de las cuentas\n"
				+ " 3)Cuentas con $10,000 o más\n "
				+ "4)Cuentas saldo a tope\n 5)Cuentas con el minimo\n 6)Salir";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:montoTotal();ciclo=false;break;
		case 2:montoPromedio();ciclo=false;break;
		case 3:mayor10mil();ciclo=false;break;
		case 4:saldoMax();ciclo=false;break;
		case 5:saldoMin();ciclo=false;break;
		case 6:ciclo=false;break;
		default:JOptionPane.showMessageDialog(null,"Ingrese aqui la opción deseada");
		  }
		}
	}
	public static void montoTotal() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
		    float montoTotal=0;
		    for(int i=0;i<c.getSize();i++) 
			    montoTotal=montoTotal+c.getItem(i).getSaldo();
		    JOptionPane.showMessageDialog(null,"El monto tota es: $"+montoTotal);
		}
	}
	
	public static void montoPromedio() {
		float montoProm=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
		    float montoTotal=0;
		    for(int i=0;i<c.getSize();i++) 
		        montoTotal=montoTotal+c.getItem(i).getSaldo();
		    montoProm=montoTotal/c.getSize(); 
		    JOptionPane.showMessageDialog(null,"El monto promedio es: $"+montoProm);
		}
	}
	
	public static void mayor10mil() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()>10000) 
					copia[i]=c.getItem(i);
			String cuentas="";
			for(int j=0;j<c.getSize();j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"Las cuentas que tienen un saldo exedente a $10,000 son:\n"+cuentas);
		}
	}
	
	public static void saldoMax() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
			int vacio=0;
			float max=c.getItem(0).getSaldo();
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()>max)
					max=c.getItem(i).getSaldo();
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()==max) 
					copia[i-vacio]=c.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"La/las cuenta/cuentas con mayor saldo es/son:\n"+cuentas);
		}
		
	}
	
	public static void saldoMin() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
			int vacio=0;
			float min=c.getItem(0).getSaldo();
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()<min)
					min=c.getItem(i).getSaldo();
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()==min) 
					copia[i-vacio]=c.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"La/las cuenta/cuentas con menor saldo es/son:\n"+cuentas);
		}
	}

}