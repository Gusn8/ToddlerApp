package com.miramicodigo.toddler.model.interactor;

/**
 * Created by gusn8 on 31-01-17.
 */

public interface ToddlerCallback {
    void onObtieneEvaluacionSuccess(Object object);
    void onObtieneEvaluacionError(Object object);

    void onObtienePreguntasSuccess(Object object);
    void onObtienePreguntasError(Object object);
}
