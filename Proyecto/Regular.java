import java.math.BigDecimal;

public class Regular extends Usuario {
    private BigDecimal comision = new BigDecimal("0.015"); //Comision del 1.5%

    public Regular(int id, int balance, String password) { 
        super(id, balance, password);
    }

    public BigDecimal get_comision() {
        return this.comision;
    }

    @Override
    public int withdraw_client(int wd_amount)throws SaldoInsuficiente{
        BigDecimal montoRetiro = new BigDecimal(wd_amount);
        BigDecimal montoComision = montoRetiro.multiply(comision);//El valor de la comision se multiplica con el monto, con el metodo Multiply

        BigDecimal nuevo_balance = new BigDecimal(this.get_balance()).subtract(montoRetiro.add(montoComision));//Se le resta el monto + comision al balance
        if(get_balance() <= montoRetiro.add(montoComision).intValue()){
            throw new SaldoInsuficiente("Oops... estás usando todo tu saldo y no te alcanza para pagar la comisión!");
            
        }else{
            update_balance(nuevo_balance.intValue());    
            System.out.println("Se realizó el retiro con éxito");
            System.out.println("Monto del retiro: " + montoRetiro+"$");
            System.out.println("Comisión aplicada: " + montoComision.intValue()+"$");
            System.out.println("Nuevo saldo: " + this.get_balance()+"$");
            return this.get_balance();
            }
        
    } 
}