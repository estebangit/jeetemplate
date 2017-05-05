package com.esteban.template.dto;

/**
 * Created by esteban on 24.03.17.
 */
public final class CustomerDto extends PersonDto {

    /**
     * Default value included to remove warning.
     **/
    private static final long serialVersionUID = 1L;

    private String reference;

    public CustomerDto() {
        super();
    }

    public CustomerDto(Long id, String firstName, String lastName, String phone, String email, String reference) {
        super(id, firstName, lastName, phone, email);
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "CustomerDto{"
                + simpleToString()
                + "reference='" + reference + '\''
                + '}';
    }

}
