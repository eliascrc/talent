package cr.talent.support.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitForeignKeyNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class CustomImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl{

    private static final String FK_PREFIX = "FK";
    @Override
    public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source){
        StringBuilder sb = new StringBuilder()
                .append( "_" ).append( source.getTableName() ).append( "_" ).append( source.getReferencedTableName())
                .append("_").append(source.getColumnNames().get(0));
        for ( Identifier columnName : source.getReferencedColumnNames() ) {
            sb.append( "_" ).append( columnName );
        }

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sb.toString().getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
            return toIdentifier(
                    FK_PREFIX + "_" + myHash,
                    source.getBuildingContext()
            );
        }catch(Exception exception){
            return null;
        }
    }
}