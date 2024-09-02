/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.exception;

/**
 *
 * @author Munja
 */
public class EntityExistsException extends ApplicationException {

    private final Object entity;

    public EntityExistsException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

}
