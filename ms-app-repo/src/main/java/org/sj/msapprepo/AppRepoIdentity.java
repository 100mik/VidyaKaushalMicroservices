package org.sj.msapprepo;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Embeddable
@Setter
@Getter
public class AppRepoIdentity implements Serializable{
	private UUID 	userID;
	private int 	schemeID;
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppRepoIdentity pk = (AppRepoIdentity) o;
        return Objects.equals(userID, pk.userID) && Objects.equals(schemeID, pk.schemeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, schemeID);
    }

}
