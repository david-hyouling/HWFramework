package gov.nist.javax.sip.parser.ims;

import gov.nist.javax.sip.header.SIPHeader;
import gov.nist.javax.sip.header.ims.PAssociatedURI;
import gov.nist.javax.sip.header.ims.PAssociatedURIList;
import gov.nist.javax.sip.parser.AddressParametersParser;
import gov.nist.javax.sip.parser.Lexer;
import gov.nist.javax.sip.parser.TokenTypes;
import java.text.ParseException;

public class PAssociatedURIParser extends AddressParametersParser {
    public PAssociatedURIParser(String associatedURI) {
        super(associatedURI);
    }

    protected PAssociatedURIParser(Lexer lexer) {
        super(lexer);
    }

    public SIPHeader parse() throws ParseException {
        String str;
        if (debug) {
            dbg_enter("PAssociatedURIParser.parse");
        }
        PAssociatedURIList associatedURIList = new PAssociatedURIList();
        try {
            headerName(TokenTypes.P_ASSOCIATED_URI);
            PAssociatedURI associatedURI = new PAssociatedURI();
            associatedURI.setHeaderName("P-Associated-URI");
            super.parse(associatedURI);
            associatedURIList.add(associatedURI);
            this.lexer.SPorHT();
            while (this.lexer.lookAhead(0) == ',') {
                this.lexer.match(44);
                this.lexer.SPorHT();
                PAssociatedURI associatedURI2 = new PAssociatedURI();
                super.parse(associatedURI2);
                associatedURIList.add(associatedURI2);
                this.lexer.SPorHT();
            }
            this.lexer.SPorHT();
            this.lexer.match(10);
            return associatedURIList;
        } finally {
            if (debug) {
                str = "PAssociatedURIParser.parse";
                dbg_leave(str);
            }
        }
    }
}
