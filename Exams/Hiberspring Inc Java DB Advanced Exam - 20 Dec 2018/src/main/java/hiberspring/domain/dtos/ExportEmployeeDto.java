package hiberspring.domain.dtos;

public class ExportEmployeeDto {
    private String name;
    private String position;
    private String cardNumber;



    public ExportEmployeeDto(String name, String position, String cardNumber) {
        this.name = name;
        this.position = position;
        this.cardNumber = cardNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return String.format("Name: {%s}\n" +
                "Position: {%s}\n" +
                "Card Number: {%s}\n",this.name,this.position,this.cardNumber);
    }
}
