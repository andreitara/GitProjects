package hibernate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 6/23/2015.
 */

@Entity
@Table(name="[EcEnablement].[dbo].[TradeRelationship]")
public class TradeRelationship {

    @Id
    @Column(name="TradeRelationshipSID")
    private long TradeRelationshipSID;

    @Column(name="TpmTransactionSID")
    private long TpmTransactionSID;

    @Column(name="ReceiverPartySID")
    private Integer ReceiverPartySID;

    @Column(name="DependencyAgreementSID")
    private Long DependencyAgreementSID;

    @Column(name="CreatedBySID")
    private Integer CreatedBySID;

    @Column(name="ModifiedBySID")
    private Integer ModifiedBySID;

    @Column(name="CreatedDateTime")
    private Date CreatedDateTime;

    @Column(name="LastModifiedDateTime")
    private Date LastModifiedDateTime;

    @Column(name="MethodID")
    private Byte MethodID;

    @Column(name="IsActive")
    private boolean IsActive;

    @Column(name="Name")
    private String Name;

    public TradeRelationship() {
    }

    public TradeRelationship(long tradeRelationshipSID, long tpmTransactionSID, Integer receiverPartySID, Long dependencyAgreementSID, Integer createdBySID, Integer modifiedBySID, Date createdDateTime, Date lastModifiedDateTime, Byte methodID, boolean isActive, String name) {
        TradeRelationshipSID = tradeRelationshipSID;
        TpmTransactionSID = tpmTransactionSID;
        ReceiverPartySID = receiverPartySID;
        DependencyAgreementSID = dependencyAgreementSID;
        CreatedBySID = createdBySID;
        ModifiedBySID = modifiedBySID;
        CreatedDateTime = createdDateTime;
        LastModifiedDateTime = lastModifiedDateTime;
        MethodID = methodID;
        IsActive = isActive;
        Name = name;
    }

    public long getTradeRelationshipSID() {
        return TradeRelationshipSID;
    }

    public void setTradeRelationshipSID(long tradeRelationshipSID) {
        TradeRelationshipSID = tradeRelationshipSID;
    }

    public long getTpmTransactionSID() {
        return TpmTransactionSID;
    }

    public void setTpmTransactionSID(long tpmTransactionSID) {
        TpmTransactionSID = tpmTransactionSID;
    }

    public Integer getReceiverPartySID() {
        return ReceiverPartySID;
    }

    public void setReceiverPartySID(Integer receiverPartySID) {
        ReceiverPartySID = receiverPartySID;
    }

    public Long getDependencyAgreementSID() {
        return DependencyAgreementSID;
    }

    public void setDependencyAgreementSID(Long dependencyAgreementSID) {
        DependencyAgreementSID = dependencyAgreementSID;
    }

    public Integer getCreatedBySID() {
        return CreatedBySID;
    }

    public void setCreatedBySID(Integer createdBySID) {
        CreatedBySID = createdBySID;
    }

    public Integer getModifiedBySID() {
        return ModifiedBySID;
    }

    public void setModifiedBySID(Integer modifiedBySID) {
        ModifiedBySID = modifiedBySID;
    }

    public Date getCreatedDateTime() {
        return CreatedDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        CreatedDateTime = createdDateTime;
    }

    public Date getLastModifiedDateTime() {
        return LastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        LastModifiedDateTime = lastModifiedDateTime;
    }

    public Byte getMethodID() {
        return MethodID;
    }

    public void setMethodID(Byte methodID) {
        MethodID = methodID;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setIsActive(boolean isActive) {
        IsActive = isActive;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeRelationship that = (TradeRelationship) o;

        if (TradeRelationshipSID != that.TradeRelationshipSID) return false;
        if (TpmTransactionSID != that.TpmTransactionSID) return false;
        if (IsActive != that.IsActive) return false;
        if (ReceiverPartySID != null ? !ReceiverPartySID.equals(that.ReceiverPartySID) : that.ReceiverPartySID != null)
            return false;
        if (DependencyAgreementSID != null ? !DependencyAgreementSID.equals(that.DependencyAgreementSID) : that.DependencyAgreementSID != null)
            return false;
        if (CreatedBySID != null ? !CreatedBySID.equals(that.CreatedBySID) : that.CreatedBySID != null) return false;
        if (ModifiedBySID != null ? !ModifiedBySID.equals(that.ModifiedBySID) : that.ModifiedBySID != null)
            return false;
        if (CreatedDateTime != null ? !CreatedDateTime.equals(that.CreatedDateTime) : that.CreatedDateTime != null)
            return false;
        if (LastModifiedDateTime != null ? !LastModifiedDateTime.equals(that.LastModifiedDateTime) : that.LastModifiedDateTime != null)
            return false;
        if (MethodID != null ? !MethodID.equals(that.MethodID) : that.MethodID != null) return false;
        return !(Name != null ? !Name.equals(that.Name) : that.Name != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (TradeRelationshipSID ^ (TradeRelationshipSID >>> 32));
        result = 31 * result + (int) (TpmTransactionSID ^ (TpmTransactionSID >>> 32));
        result = 31 * result + (ReceiverPartySID != null ? ReceiverPartySID.hashCode() : 0);
        result = 31 * result + (DependencyAgreementSID != null ? DependencyAgreementSID.hashCode() : 0);
        result = 31 * result + (CreatedBySID != null ? CreatedBySID.hashCode() : 0);
        result = 31 * result + (ModifiedBySID != null ? ModifiedBySID.hashCode() : 0);
        result = 31 * result + (CreatedDateTime != null ? CreatedDateTime.hashCode() : 0);
        result = 31 * result + (LastModifiedDateTime != null ? LastModifiedDateTime.hashCode() : 0);
        result = 31 * result + (MethodID != null ? MethodID.hashCode() : 0);
        result = 31 * result + (IsActive ? 1 : 0);
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        return result;
    }
}
